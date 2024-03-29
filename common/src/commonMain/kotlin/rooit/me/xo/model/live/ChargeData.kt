package rooit.me.xo.model.live


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class ChargeData(
    @SerialName("chargeClass")
    val chargeClass: List<ChargeClas>?,
    @SerialName("marquee")
    val marquee: Marquee?
)