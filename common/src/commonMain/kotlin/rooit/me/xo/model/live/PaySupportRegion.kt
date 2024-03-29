package rooit.me.xo.model.live


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class PaySupportRegion(
    @SerialName("countryCode")
    val countryCode: String?,
    @SerialName("icon")
    val icon: String?,
    @SerialName("name")
    val name: String?
)