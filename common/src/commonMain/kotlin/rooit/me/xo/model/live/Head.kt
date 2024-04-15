package rooit.me.xo.model.live


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class Head(
    @SerialName("icon")
    val icon: String?,
    @SerialName("subIcon")
    val subIcon: String?,
    @SerialName("subTitle")
    val subTitle: String?,
    @SerialName("title")
    val title: String?
)