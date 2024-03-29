package rooit.me.xo.model.live


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class WithDrawInfo(
    @SerialName("balance")
    val balance: String?,
    @SerialName("coin")
    val coin: String?,
    @SerialName("maxWithdraw")
    val maxWithdraw: Int?,
    @SerialName("minWithdraw")
    val minWithdraw: Int?,
    @SerialName("needFlow")
    val needFlow: String?,
    @SerialName("new_coin")
    val newCoin: String?,
    @SerialName("noWithdrawAmount")
    val noWithdrawAmount: Int?,
    @SerialName("support_withdraw_type")
    val supportWithdrawType: List<SupportWithdrawType?>?,
    @SerialName("tip")
    val tip: String?,
    @SerialName("withdraw_config")
    val withdrawConfig: List<WithdrawConfig?>?
)