package rooit.me.xo.model.live


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class Level(
    @SerialName("colour")
    val colour: String?,
    @SerialName("levelid")
    val levelid: String?,
    @SerialName("thumb")
    val thumb: String?,
    @SerialName("thumb_mark")
    val thumbMark: String?
)