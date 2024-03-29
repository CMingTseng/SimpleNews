package rooit.me.xo.model.live


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class LotteryInfo(
    @SerialName("defaultLotteryType")
    val defaultLotteryType: Int?,
    @SerialName("lotteryList")
    val lotteryList: List<Lottery?>?
)