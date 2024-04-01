package rooit.me.xo.model.live


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginInfo(
    @SerialName("acd_device")
    val acdDevice: String?,
    @SerialName("avatar")
    val avatar: String?,
    @SerialName("avatar_thumb")
    val avatarThumb: String?,
    @SerialName("birthday")
    val birthday: String?,
    @SerialName("city")
    val city: String?,
    @SerialName("coin")
    val coin: String?,
    @SerialName("consumption")
    val consumption: String?,
    @SerialName("dfk")
    val dfk: Boolean?,
    @SerialName("dsm")
    val dsm: Boolean?,
    @SerialName("id")
    val id: String?,
    @SerialName("isagent")
    val isagent: String?,
    @SerialName("isreg")
    val isreg: String?,
    @SerialName("last_login_time")
    val lastLoginTime: String?,
    @SerialName("left_video_times")
    val leftVideoTimes: Int?,
    @SerialName("level")
    val level: Int?,
    @SerialName("level_anchor")
    val levelAnchor: Int?,
    @SerialName("login_type")
    val loginType: String?,
    @SerialName("province")
    val province: String?,
    @SerialName("sex")
    val sex: String?,
    @SerialName("signature")
    val signature: String?,
    @SerialName("source")
    val source: String?,
    @SerialName("token")
    val token: String?,
    @SerialName("user_nicename")
    val userNicename: String?,
    @SerialName("votestotal")
    val votestotal: String?
)