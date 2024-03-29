package rooit.me.xo.model.live


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class UsersBankType(
    @SerialName("icon")
    val icon: String?,
    @SerialName("id")
    val id: String?,
    @SerialName("no_withdrawcharge_config")
    val noWithdrawchargeConfig: NoWithdrawchargeConfig?,
    @SerialName("num")
    val num: String?,
    @SerialName("title")
    val title: String?
)