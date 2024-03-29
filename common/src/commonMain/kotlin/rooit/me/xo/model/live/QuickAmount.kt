package rooit.me.xo.model.live


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class QuickAmount(
    @SerialName("give_rate")
    val giveRate: String?,
    @SerialName("subTitle")
    val subTitle: String?,
    @SerialName("title")
    val title: String?,

)