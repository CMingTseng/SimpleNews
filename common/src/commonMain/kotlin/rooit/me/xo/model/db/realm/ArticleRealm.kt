package rooit.me.xo.model.db.realm

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import kotlinx.serialization.SerialName
import org.mongodb.kbson.ObjectId
import rooit.me.xo.model.Article
import rooit.me.xo.model.Source

public open class ArticleRealm(
    @PrimaryKey
    public var _id: ObjectId = ObjectId(),
    @SerialName("author")
    public var author: String? = null,
    @SerialName("content")
    public var content: String? = null,
    @SerialName("description")
    public var description: String? = null,
    @SerialName("publishedAt")
    public var publishedAt: String? = null,
    @SerialName("source")
    public var source: SourceRealm? = null,
    @SerialName("title")
    public var title: String? = null,
    @SerialName("url")
    public var url: String? = null,
    @SerialName("urlToImage")
    public var urlToImage: String? = null
): RealmObject {
    public constructor() : this(_id = ObjectId())
}

public fun convertToArticleList(articleRealmList: List<ArticleRealm>): List<Article> {
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

public fun convertToSource(sourceRealm: SourceRealm?): Source? {
    return sourceRealm?.let {
        Source(
            id = it.id,
            name = it.name
        )
    }
}