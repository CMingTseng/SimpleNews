package rooit.me.xo.model.live


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class WithdrawalBigType(
    @SerialName("icon")
    val icon: String?,
    @SerialName("icon_selected")
    val iconSelected: String?,
    @SerialName("id")
    val id: String?,
    @SerialName("num")
    val num: String?,
    @SerialName("title")
    val title: String?
)