package rooit.me.xo.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class News(
    @SerialName("articles")
    val articles: List<Article>,
    @SerialName("status")
    val status: String,
    @SerialName("totalResults")
    val totalResults: Int
)