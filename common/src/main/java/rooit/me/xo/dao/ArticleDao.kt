package rooit.me.xo.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow
import rooit.me.xo.model.db.room.ArticleEntity

@Dao
interface ArticleDao {
    /**
     * Upsert = Update + Insert.
     * 如果傳入的 ArticleEntity 的主鍵 (url) 已存在，則更新該行。
     * 如果不存在，則插入新行。這完美符合我們的需求。
     *   @Insert(onConflict = OnConflictStrategy.REPLACE)
     */
    @Upsert
    suspend fun upsertArticles(articles: List<ArticleEntity>)

    /**
     * 根據 title 和 content 查詢文章。
     * 用於實現「如果 title 和 content 相同就不更新」的邏輯。
     */
    @Query("SELECT * FROM articles WHERE title = :title AND content = :content LIMIT 1")
    suspend fun findArticleByTitleAndContent(title: String, content: String?): ArticleEntity?

    /**
     * 查詢所有文章，並按發布時間降序排列。
     * 返回一個 Flow，當資料庫變動時，它會自動發射最新的資料列表。
     */
    @Query("SELECT * FROM articles ORDER BY publishedAt DESC")
    fun observeAllArticles(): Flow<List<ArticleEntity>>

    /**
     * 刪除指定的文章。
     */
//    @Delete
//    suspend fun deleteArticle(article: ArticleEntity)
    /**
     * 修改：根據主鍵 (url) 刪除文章。
     */
    @Query("DELETE FROM articles WHERE url = :url")
    suspend fun deleteArticle(url: String)

    /**
     * 刪除所有文章。
     */
    @Query("DELETE FROM articles")
    suspend fun deleteAllArticles()
}