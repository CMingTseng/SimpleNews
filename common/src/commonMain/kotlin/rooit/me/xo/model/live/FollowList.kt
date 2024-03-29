package rooit.me.xo.model.live


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class FollowList(
    @SerialName("des")
    val des: String?,
    @SerialName("list")
    val list: List<StudioInfo>?,
    @SerialName("title")
    val title: String?
)