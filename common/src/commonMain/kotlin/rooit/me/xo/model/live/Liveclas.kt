package rooit.me.xo.model.live


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class Liveclas(
    @SerialName("des")
    val des: String?,
    @SerialName("id")
    val id: String?,
    @SerialName("name")
    val name: String?,
    @SerialName("orderno")
    val orderno: String?,
    @SerialName("thumb")
    val thumb: String?
)