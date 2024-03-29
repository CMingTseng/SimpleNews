package rooit.me.xo.model.live


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class PayConfig(
    @SerialName("data")
    val chargedata: ChargeData?,
    @SerialName("pay_current_region")
    val payCurrentRegion: PayCurrentRegion?,
    @SerialName("pay_support_regions")
    val paySupportRegions: List<PaySupportRegion>?
)