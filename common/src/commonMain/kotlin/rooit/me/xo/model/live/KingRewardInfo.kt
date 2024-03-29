package rooit.me.xo.model.live


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class KingRewardInfo(
    @SerialName("leve_c_cur")
    val leveCCur: String?,
    @SerialName("leve_c_end")
    val leveCEnd: String?,
    @SerialName("leve_c_start")
    val leveCStart: String?,
    @SerialName("level")
    val level: String?,
    @SerialName("list")
    val list: List<KingRewardDetail>?,
    @SerialName("reward_list")
    val rewardList: List<Reward?>?
)