package rooit.me.xo.model.live


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class Item(
    @SerialName("item_icon")
    val itemIcon: String?,
    @SerialName("item_name")
    val itemName: String?,
    @SerialName("item_num")
    val itemNum: String?
)