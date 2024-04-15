package rooit.me.xo.model.live


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class Reward(
    @SerialName("reward_can_get")
    val rewardCanGet: Int?,
    @SerialName("reward_got_money")
    val rewardGotMoney: Int?,
    @SerialName("reward_money")
    val rewardMoney: String?,
    @SerialName("reward_next_time")
    val rewardNextTime: Int?,
    @SerialName("reward_process")
    val rewardProcess: Int?,
    @SerialName("reward_title")
    val rewardTitle: String?,
    @SerialName("reward_total_count")
    val rewardTotalCount: Int?,
    @SerialName("reward_type")
    val rewardType: Int?
)