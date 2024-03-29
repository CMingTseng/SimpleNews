package rooit.me.xo.model.live


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import rooit.me.xo.api.live.SELF

@Serializable
public data class Liang(
    @SerialName("name")
    val name: String?
)
public typealias UserVipId = Liang
