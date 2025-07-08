package rooit.me.xo.repository

import io.realm.kotlin.Realm
import io.realm.kotlin.delete
import io.realm.kotlin.ext.query
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import rooit.me.xo.api.NewsApi
import rooit.me.xo.model.Article
import rooit.me.xo.model.Source
import rooit.me.xo.model.db.realm.ArticleRealm
import rooit.me.xo.model.db.realm.SourceRealm
import timber.log.Timber
import java.io.IOException
import java.util.concurrent.atomic.AtomicBoolean

class NewsRepository(private val api: NewsApi, private val realm: Realm) {

    // AtomicBoolean to prevent multiple simultaneous network fetches if needed by ViewModel logic
    private val isCurrentlyFetchingNetwork = AtomicBoolean(false)

    /**
     * Observes articles from the local Realm database.
     * Emits a list of domain Article objects.
     */
    fun observeArticlesFromDB(): Flow<List<Article>> = realm.query<ArticleRealm>().asFlow()
        .map { resultsChange ->
            // Map RealmResults to a plain list of ArticleRealm
            resultsChange.list.toList() // Ensure it's a stable list for further mapping
        }
        .map { realmArticles ->
            // Map ArticleRealm list to domain Article list
            realmArticles.map { it.toDomain() }
        }
        .distinctUntilChanged() // Only emit if the list content actually changes
        .flowOn(Dispatchers.Default) // Perform Realm operations and mapping on a background thread

    /**
     * Fetches top headlines from the network API.
     * This is a suspend function that returns the result directly.
     * The ViewModel will handle wrapping this in a Flow or error handling as needed.
     */
    suspend fun fetchTopHeadlinesFromNetwork(country: String, apiKey: String): List<Article> {
        // Simple guard against concurrent calls, can be more sophisticated
        if (isCurrentlyFetchingNetwork.get()) {
            Timber.d("Network fetch already in progress, skipping.")
            return emptyList() // Or throw a specific exception if preferred
        }
        isCurrentlyFetchingNetwork.set(true)
        try {
            Timber.d("Fetching from network: country=$country")
            val response = api.fetchTopHeadlines(country, apiKey) // Assuming this is your Retrofit call
            return response.articles ?: emptyList()
        } catch (e: IOException) {
            Timber.e(e, "Network IOException while fetching headlines")
            throw e // Re-throw to be caught by ViewModel
        } catch (e: Exception) { // Catch other potential exceptions from API call
            Timber.e(e, "General Exception while fetching headlines from network")
            throw e // Re-throw
        } finally {
            isCurrentlyFetchingNetwork.set(false)
        }
    }

    /**
     * Alternative: Fetches top headlines from the network and emits them as a Flow.
     * This version handles its own try-catch and emits results or errors.
     * ViewModel might prefer the suspend function version for more control.
     */
    fun fetchTopHeadlinesNetworkFlow(country: String, apiKey: String): Flow<List<Article>> = flow {
        try {
            if (isCurrentlyFetchingNetwork.compareAndSet(false, true)) {
                Timber.d("Fetching (flow) from network: country=$country")
                val response = api.fetchTopHeadlines(country, apiKey)
                emit(response.articles ?: emptyList())
                isCurrentlyFetchingNetwork.set(false) // Reset after successful emission
            } else {
                Timber.d("Network fetch (flow) already in progress, skipping emission.")
                // Optionally emit current DB state or an empty list if skipping
            }
        } catch (e: IOException) {
            Timber.e(e, "Network IOException in fetchTopHeadlinesNetworkFlow")
            throw e // Let the collector in ViewModel handle this
        } catch (e: Exception) {
            Timber.e(e, "General Exception in fetchTopHeadlinesNetworkFlow")
            throw e // Let the collector in ViewModel handle this
        } finally {
            // Ensure the flag is reset if an exception occurs before emit and it was set
            if(isCurrentlyFetchingNetwork.get()) isCurrentlyFetchingNetwork.set(false)
        }
    }.flowOn(Dispatchers.IO) // Perform network operations on IO dispatcher

    /**
     * Syncs a list of domain Article objects into the local Realm database.
     * This operation clears existing articles and sources before writing new ones.
     */
    suspend fun syncArticlesToDB(articles: List<Article>) = withContext(Dispatchers.Default) {
        if (articles.isEmpty()) {
            Timber.d("No articles to sync to DB.")
            // Optionally clear DB if an empty list means everything should be wiped
            // realm.write {
            //    delete<SourceRealm>()
            //    delete<ArticleRealm>()
            // }
            return@withContext
        }
        Timber.d("Syncing ${articles.size} articles to DB.")
        realm.write {
            // Consider more granular updates if needed, instead of full delete/re-insert
            val existingArticleUrls = query<ArticleRealm>().find().map { it.url }.toSet()
            delete<SourceRealm>() // This might be too broad if sources are shared or stable
            delete<ArticleRealm>()

            articles.forEach { article ->
                copyToRealm(article.toRealmObject())
            }
        }
    }

    // Expose the fetching state if ViewModel needs to check it
    fun isCurrentlyFetchingFromNetwork(): Boolean = isCurrentlyFetchingNetwork.get()
}

private fun ArticleRealm.toDomain(): Article {
    return Article(
        author = author,
        content = content,
        description = description,
        publishedAt = publishedAt,
        source = Source(
            name = source?.name,
            id = source?.id
        ),
        title = title,
        url = url,
        urlToImage = urlToImage
    )
}


private fun Article.toRealmObject(): ArticleRealm {
    return ArticleRealm(
        author = author,
        content = content,
        description = description,
        publishedAt = publishedAt,
        source = SourceRealm(
            name = source?.name,
            id = source?.id
        ),
        title = title,
        url = url,
        urlToImage = urlToImage
    )
}