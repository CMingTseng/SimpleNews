package rooit.me.xo.model.db.room

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "articles")
data class ArticleEntity(
    // 我們使用 URL 作為主鍵，因為它通常是唯一的。
    @PrimaryKey
    val url: String,
    val author: String?,
    val content: String?,
    val description: String?,
    val publishedAt: String?,
    // 將 SourceEntity 嵌入到 ArticleEntity 表中
    @Embedded(prefix = "source_") // prefix 防止欄位名稱衝突
    val source: SourceEntity,
    val title: String, // title 不能是 nullable，因為我們要用它做比對
    val urlToImage: String?
)