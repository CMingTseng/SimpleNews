package rooit.me.xo.model.live


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class Lottery(
    @SerialName("logo")
    val logo: String?,
    @SerialName("lotteryType")
    val lotteryType: Int?,
    @SerialName("name")
    val name: String?
)