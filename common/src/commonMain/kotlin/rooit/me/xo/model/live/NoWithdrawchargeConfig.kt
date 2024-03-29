package rooit.me.xo.model.live


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class NoWithdrawchargeConfig(
    @SerialName("give_rate")
    val giveRate: List<GiveRate?>?,
    @SerialName("quick_amount")
    val quickAmount: List<QuickAmount?>?
)