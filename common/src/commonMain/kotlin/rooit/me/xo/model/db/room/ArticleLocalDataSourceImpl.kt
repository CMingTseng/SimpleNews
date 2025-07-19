package rooit.me.xo.model.db.room

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import rooit.me.xo.dao.ArticleDao
import rooit.me.xo.gateway.ArticleLocalDataSource
import rooit.me.xo.utils.DispatcherProvider

/**
 * 使用 Room DAO 實作的 ArticleLocalDataSource。
 * 這是將抽象介面與 Room 連接起來的橋樑。
 * 負責將所有資料庫操作切換到 I/O 執行緒，並管理資料庫的生命週期。
 *
 * @param dispatcherProvider 提供協程 Dispatcher。
 * @param articleDao 由 Room 產生的 Data Access Object。
 * @param newsDatabase Room Database 的實例，用於關閉操作。
 */
//class ArticleLocalDataSourceImpl(private val articleDao: ArticleDao) : ArticleLocalDataSource {
class ArticleLocalDataSourceImpl(
    private val dispatcherProvider: DispatcherProvider,
    private val articleDao: ArticleDao,
    private val newsDatabase: NewsDatabase
) : ArticleLocalDataSource {

//    override fun observeAllArticles(): Flow<List<ArticleEntity>> {
//        return articleDao.observeAllArticles()
//    }
    override fun observeAllArticles(): Flow<List<ArticleEntity>> {
        return articleDao.observeAllArticles()
    }

//    override suspend fun upsertArticles(articles: List<ArticleEntity>) {
//        articleDao.upsertArticles(articles)
//    }
    override suspend fun upsertArticles(articles: List<ArticleEntity>) =
        withContext(dispatcherProvider.io) {
            articleDao.upsertArticles(articles)
        }

//    override suspend fun findArticleByTitleAndContent(title: String, content: String?): ArticleEntity? {
//        return articleDao.findArticleByTitleAndContent(title, content)
//    }
    override suspend fun findArticleByTitleAndContent(
        title: String,
        content: String?
    ): ArticleEntity? = withContext(dispatcherProvider.io) {
        articleDao.findArticleByTitleAndContent(title, content)
    }

//    override suspend fun deleteArticle(article: ArticleEntity) {
//        articleDao.deleteAllArticles()
//    }

//    override suspend fun deleteArticle(url: String) {
//        articleDao.deleteArticle(url)
//    }
    override suspend fun deleteArticle(url: String) = withContext(dispatcherProvider.io) {
        articleDao.deleteArticle(url)
    }

    //    override suspend fun deleteAllArticles() {
//        articleDao.deleteAllArticles()
//    }
    override suspend fun deleteAllArticles() = withContext(dispatcherProvider.io) {
        articleDao.deleteAllArticles()
    }

    override suspend fun closeDB() = withContext(dispatcherProvider.io) {
        newsDatabase.close()
    }
}