package rooit.me.xo.mappers

import rooit.me.xo.model.Article
import rooit.me.xo.model.Source
import rooit.me.xo.model.db.room.ArticleEntity
import rooit.me.xo.model.db.room.SourceEntity

fun Article.toEntity(): ArticleEntity {
    return ArticleEntity(
        url = this.url ?: "", // 確保 url 不為空，因為它是主鍵
        author = this.author,
        content = this.content,
        description = this.description,
        publishedAt = this.publishedAt,
        source = SourceEntity(id = this.source?.id, name = this.source?.name),
        title = this.title ?: "No Title", // 確保 title 不為空
        urlToImage = this.urlToImage
    )
}

// Entity to Domain model
fun ArticleEntity.toDomain(): Article {
    return Article(
        url = this.url,
        author = this.author,
        content = this.content,
        description = this.description,
        publishedAt = this.publishedAt,
        source = Source(id = this.source.id, name = this.source.name),
        title = this.title,
        urlToImage = this.urlToImage
    )
}

//fun Article.toRealmObject(): ArticleRealm {
//    return ArticleRealm(
//        author = author,
//        content = content,
//        description = description,
//        publishedAt = publishedAt,
//        source = SourceRealm(
//            name = source?.name,
//            id = source?.id
//        ),
//        title = title,
//        url = url,
//        urlToImage = urlToImage
//    )
//}
//
//fun ArticleRealm.toDomain(): Article {
//    return Article(
//        author = author,
//        content = content,
//        description = description,
//        publishedAt = publishedAt,
//        source = Source(
//            name = source?.name,
//            id = source?.id
//        ),
//        title = title,
//        url = url,
//        urlToImage = urlToImage
//    )
//}