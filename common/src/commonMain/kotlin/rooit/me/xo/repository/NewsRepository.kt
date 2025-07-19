package rooit.me.xo.repository

import rooit.me.xo.utils.log.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import rooit.me.xo.api.NewsApi
import rooit.me.xo.mappers.toDomain
import rooit.me.xo.mappers.toEntity
import rooit.me.xo.model.Article
import rooit.me.xo.model.db.room.ArticleEntity
import kotlinx.coroutines.flow.onStart
import rooit.me.xo.gateway.ArticleLocalDataSource

/**
 * 負責處理新聞資料的單一來源 (Single Source of Truth)。
 * 協調從遠端 API 和本地資料庫獲取資料。
 *
 * @param api 遠端新聞 API 的 Ktorfit 介面。
 * @param localDataSource 本地資料庫的抽象介面。
 */
class NewsRepository(
    private val api: NewsApi,
    private val localDataSource: ArticleLocalDataSource
) {

    /**
     * 獲取頭條新聞文章。
     * 此函式返回一個 "冷" Flow，它會：
     * 1. 首先立即發射本地資料庫中快取的文章。
     * 2. 在 Flow 開始被收集時 (`onStart`)，觸發一次背景網路請求來獲取最新資料。
     * 3. 網路請求成功後，將新資料寫入資料庫，這會自動觸發 Flow 發射更新後的文章列表。
     *
     * @param country 要查詢的國家代碼 (例如 "us")。
     * @param apiKey 你的 NewsAPI 金鑰。
     * @return 一個會持續發射文章列表的 Flow<List<Article>>。
     */
    fun getArticles(country: String, apiKey: String): Flow<List<Article>> {
        return localDataSource.observeAllArticles()
            .map { entities ->
                Log.e("NewsRepository","Show me entities $entities ")
                // 將資料庫實體 (Entity) 列表轉換為領域模型 (Domain Model) 列表
                entities.map { it.toDomain() }

            }
            .onStart {
                // 當有收集者開始監聽此 Flow 時 (例如 ViewModel 的 viewModelScope)，
                // 這個區塊的程式碼會被執行。
                try {
                    Log.e("NewsRepository","onStart: Fetching fresh articles from network...")
                    val networkResponse = api.fetchTopHeadlines(country, apiKey)
                    syncArticlesToDB(networkResponse.articles)
                } catch (e: Exception) {
                    // 如果網路請求失敗，我們只記錄錯誤，但不會中斷整個 Flow。
                    // UI 仍然可以顯示來自資料庫的舊資料。
                    Log.e("NewsRepository","Network fetch failed in onStart $e" )
                }
            }
            // 確保所有上游操作 (map, onStart 裡的網路請求, DB查詢) 都在 IO 線程池中執行。
            .flowOn(Dispatchers.IO)
    }

    /**
     * 將從網路獲取的文章同步到本地資料庫。
     * 會比對 title 和 content，只插入不存在的文章。
     */
    private suspend fun syncArticlesToDB(articles: List<Article>) {
        if (articles.isEmpty()) {
            return
        }
        Log.e("NewsRepository","Syncing ${articles.size} articles to DB.")

        val articlesToInsert = mutableListOf<ArticleEntity>()
        for (article in articles) {
            if (article.title.isNullOrBlank() || article.url.isNullOrBlank()) {
                continue // 跳過無效資料
            }

            val existing = localDataSource.findArticleByTitleAndContent(article.title, article.content)
            if (existing == null) {
                articlesToInsert.add(article.toEntity())
            } else {
                Log.e("NewsRepository","Article '${article.title}' already exists. Skipping.")
            }
        }

        if (articlesToInsert.isNotEmpty()) {
            Log.e("NewsRepository","Upserting ${articlesToInsert.size} new/updated articles.")
            localDataSource.upsertArticles(articlesToInsert)
        }
    }

    /**
     * 提供刪除單一文章的功能。
     */
//    suspend fun deleteArticle(article: Article) {
//        Napier.d("Deleting article: ${article.title}")
//        localDataSource.deleteArticle(article.toEntity())
//    }
    suspend fun deleteArticle(article: Article) {
        // 確保 article.url 不為 null 或空
        article.url?.let { url ->
            Log.e("NewsRepository","Deleting article: ${article.title}")
            // 修改：傳入 url 而不是整個 entity
            localDataSource.deleteArticle(url)
        }
    }

    /**
     * 提供刪除所有文章的功能。
     */
    suspend fun clearAllArticles() {
        Log.e("NewsRepository","Clearing all articles from DB.")
        localDataSource.deleteAllArticles()
    }
}