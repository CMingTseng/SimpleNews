package rooit.me.xo.model.live


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class SubContent(
    @SerialName("content")
    val content: Content?,
    @SerialName("type")
    val type: String?,
    @SerialName("viewType")
    val viewType: String?
)