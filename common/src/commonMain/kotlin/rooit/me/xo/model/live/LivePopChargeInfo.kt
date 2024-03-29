package rooit.me.xo.model.live


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class LivePopChargeInfo(
    @SerialName("item")
    val item: List<Item?>?,
    @SerialName("label")
    val label: String?,
    @SerialName("tip_end")
    val tipEnd: String?,
    @SerialName("tip_mid")
    val tipMid: String?,
    @SerialName("title")
    val title: String?
)