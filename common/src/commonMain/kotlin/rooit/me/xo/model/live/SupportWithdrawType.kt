package rooit.me.xo.model.live


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class SupportWithdrawType(
    @SerialName("icon")
    val icon: String?,
    @SerialName("id")
    val id: String?,
    @SerialName("num")
    val num: String?,
    @SerialName("plat_ids")
    val platIds: String?,
    @SerialName("status")
    val status: String?,
    @SerialName("title")
    val title: String?,
    @SerialName("type_id")
    val typeId: String?
)