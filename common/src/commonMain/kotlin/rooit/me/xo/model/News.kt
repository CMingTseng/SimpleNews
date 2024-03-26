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

public sealed class NewAction {
    public data object Load : NewAction()
    public data class NewState(
        var news: List<Article> = emptyList()
    )
}