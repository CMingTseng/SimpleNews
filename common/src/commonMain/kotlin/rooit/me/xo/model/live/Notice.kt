package rooit.me.xo.model.live


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class Notice(
    @SerialName("content")
    val content: String?,
    @SerialName("rate")
    val rate: String?,
    @SerialName("title")
    val title: String?,
    @SerialName("type")
    val type: String?
)