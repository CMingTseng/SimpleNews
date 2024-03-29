package rooit.me.xo.model.live


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class WithdrawConfig(
    @SerialName("users_bank_types")
    val usersBankTypes: List<UsersBankType?>?,
    @SerialName("withdrawal_big_type")
    val withdrawalBigType: WithdrawalBigType?
)