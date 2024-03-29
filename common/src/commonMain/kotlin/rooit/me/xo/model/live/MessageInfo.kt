package rooit.me.xo.model.live


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class MessageInfo(
    @SerialName("addtime")
    val addtime: String?,
    @SerialName("content")
    val content: String?
)