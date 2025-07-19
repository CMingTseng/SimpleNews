package rooit.me.xo.gateway

import kotlinx.coroutines.flow.Flow
import rooit.me.xo.model.db.room.ArticleEntity

/**
 * 本地資料存取的抽象介面 (Gateway)。
 * Repository 將依賴此介面，以和底層的資料庫實作 (如 Room) 解耦。
 */
interface ArticleLocalDataSource {
    /**
     * 以 Flow 的形式觀察所有文章的變化。
     */
    fun observeAllArticles(): Flow<List<ArticleEntity>>

    /**
     * 插入或更新文章列表。
     * @param articles 要存入資料庫的文章實體列表。
     */
    suspend fun upsertArticles(articles: List<ArticleEntity>)

    /**
     * 根據標題和內容查找特定文章，用於避免重複插入。
     * @param title 文章標題。
     * @param content 文章內容。
     * @return 如果找到，返回對應的 ArticleEntity；否則返回 null。
     */
    suspend fun findArticleByTitleAndContent(title: String, content: String?): ArticleEntity?

    /**
     * 刪除單一文章。
     * @param article 要刪除的文章實體。
     */
//    suspend fun deleteArticle(article: ArticleEntity)
    /**
     * 修改：參數改為主鍵 url。
     */
    suspend fun deleteArticle(url: String)

    /**
     * 刪除所有文章。
     */
    suspend fun deleteAllArticles()

    suspend fun closeDB()
}