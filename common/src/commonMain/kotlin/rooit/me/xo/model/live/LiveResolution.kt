package rooit.me.xo.model.live


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class LiveResolution(
    @SerialName("bAuto")
    val bAuto: Boolean?,
    @SerialName("bitrate")
    val bitrate: Int?,
    @SerialName("height")
    val height: Int?,
    @SerialName("width")
    val width: Int?
)