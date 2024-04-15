package rooit.me.xo.model.live


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class Marquee(
    @SerialName("msg")
    val msg: List<String?>?
)