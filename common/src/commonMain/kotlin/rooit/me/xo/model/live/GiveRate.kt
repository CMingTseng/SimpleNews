package rooit.me.xo.model.live


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class GiveRate(
    @SerialName("title")
    val title: String?
)