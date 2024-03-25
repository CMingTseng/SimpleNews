package rooit.me.xo.model.db.realm

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import kotlinx.serialization.SerialName
import org.mongodb.kbson.ObjectId
import rooit.me.xo.model.Article
import rooit.me.xo.model.Source

open class ArticleRealm(
    @PrimaryKey
    var _id: ObjectId = ObjectId(),
    @SerialName("author")
    var author: String? = null,
    @SerialName("content")
    var content: String? = null,
    @SerialName("description")
    var description: String? = null,
    @SerialName("publishedAt")
    var publishedAt: String? = null,
    @SerialName("source")
    var source: SourceRealm? = null,
    @SerialName("title")
    var title: String? = null,
    @SerialName("url")
    var url: String? = null,
    @SerialName("urlToImage")
    var urlToImage: String? = null
): RealmObject {
    constructor() : this(_id = ObjectId())
}

fun convertToArticleList(articleRealmList: List<ArticleRealm>): List<Article> {
    return articleRealmList.map { articleRealm ->
        Article(
            author = articleRealm.author,
            content = articleRealm.content,
            description = articleRealm.description,
            publishedAt = articleRealm.publishedAt,
            source = convertToSource(articleRealm.source),
            title = articleRealm.title,
            url = articleRealm.url,
            urlToImage = articleRealm.urlToImage
        )
    }
}

fun convertToSource(sourceRealm: SourceRealm?): Source? {
    return sourceRealm?.let {
        Source(
            id = it.id,
            name = it.name
        )
    }
}