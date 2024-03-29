package rooit.me.xo.model.live


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class Levelanchor(
    @SerialName("levelid")
    val levelid: String?,
    @SerialName("thumb")
    val thumb: String?,
    @SerialName("thumb_mark")
    val thumbMark: String?
)