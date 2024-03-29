package rooit.me.xo.model.live


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class KingRewardDetail(
    @SerialName("c_charge")
    val cCharge: String?,
    @SerialName("level")
    val level: String?,
    @SerialName("level_icon")
    val levelIcon: String?,
    @SerialName("levelup_reward")
    val levelupReward: String?,
    @SerialName("week_reward")
    val weekReward: String?
)