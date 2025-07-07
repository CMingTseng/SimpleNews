package rooit.me.xo.repository

import android.net.http.HttpException
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

class NewsRepository(private val api: NewsApi, private val realm: Realm) {
    fun fetchTopHeadlinesFromDBFlow() = realm.query<ArticleRealm>().asFlow()
        .map { it.list }
        .distinctUntilChanged()
        .map { list -> list.map { it.toBean() } }
        .flowOn(Dispatchers.Default)

    suspend fun fetchTopHeadlines(country: String, apiKey: String) =
        api.fetchTopHeadlines(country, apiKey)

    fun fetchTopHeadlinesFlow(country: String, apiKey: String): Flow<List<Article>> = flow {
        try {
            api.fetchTopHeadlines(country, apiKey).apply {
                val articles = this.articles?: emptyList()
                emit(articles)

            }
        } catch (throwable: Throwable) {
            Timber.e("Show me  fetchTopHeadlines get throwable $throwable")
            // TODO here emit Throwable
            //     Ref : https://blog.csdn.net/jungle_pig/article/details/105725160
            //     Ref : https://blog.csdn.net/zhaoyanjun6/article/details/121754941
            //     Ref : https://blog.csdn.net/qq_42467528/article/details/127225747
            when (throwable) {
                is IOException -> {

                }

                else -> {

                }
            }
        }
    }

    suspend fun syncResultIntoDB(articles: List<Article>) = withContext(Dispatchers.Default) {
        realm.write {
            delete<SourceRealm>()
            delete<ArticleRealm>()
            articles.map { article ->
                copyToRealm(article.toDb())
            }
        }
    }
}

private fun ArticleRealm.toBean(): Article {
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


private fun Article.toDb(): ArticleRealm {
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