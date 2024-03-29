package rooit.me.xo.model.live


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class ChargeClas(
    @SerialName("head")
    val head: Head?,
    @SerialName("models")
    val models: List<Model>?
)