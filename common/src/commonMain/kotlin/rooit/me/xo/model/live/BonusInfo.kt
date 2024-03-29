package rooit.me.xo.model.live


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class BonusInfo(
    @SerialName("bonus_day")
    val bonusDay: String?,
    @SerialName("bonus_switch")
    val bonusSwitch: String?,
    @SerialName("count_day")
    val countDay: String?,
//    @SerialName("bonus_list")
//    val bonusList: List<Any?>?,
)