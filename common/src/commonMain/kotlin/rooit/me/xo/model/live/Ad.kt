package rooit.me.xo.model.live


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import rooit.me.xo.api.live.SELF

@Serializable
public data class Ad(
    @SerialName("image")
    val image: String,
    @SerialName("show_type")
    val show_type: String,
    @SerialName("url")
    val url: String,
    @SerialName("pos")
    val pos: Int,
    @SerialName("desc")
    val desc: String?,
    @SerialName("dump_url")
    val dumpUrl: String?,
    @SerialName("kindID")
    val kindID: String?,
    @SerialName("maintain")
    val maintain: Boolean?,
    @SerialName("plat")
    val plat: String?,
    @SerialName("src")
    val src: String?,
    @SerialName("type")
    val type: String?,
)
public typealias UserAdBean = Ad
