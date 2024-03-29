package rooit.me.xo.model.live


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class HomePageInfo(
    @SerialName("ad_list")
    val adList: List<Ad?>?,
    @SerialName("address")
    val address: String?,
    @SerialName("apk_des")
    val apkDes: String?,
    @SerialName("apk_ewm")
    val apkEwm: String?,
    @SerialName("apk_url")
    val apkUrl: String?,
    @SerialName("apk_ver")
    val apkVer: String?,
    @SerialName("app_android")
    val appAndroid: String?,
    @SerialName("app_ios")
    val appIos: String?,
    @SerialName("AuditSwitch")
    val auditSwitch: Int?,
    @SerialName("chat_service_url")
    val chatServiceUrl: String?,
    @SerialName("check_state")
    val checkState: Boolean?,
    @SerialName("cloudtype")
    val cloudtype: String?,
    @SerialName("copyright")
    val copyright: String?,
    @SerialName("default_old_view")
    val defaultOldView: Boolean?,
    @SerialName("default_sendred_des")
    val defaultSendredDes: String?,
    @SerialName("DialingCode")
    val dialingCode: String?,
    @SerialName("enter_tip_level")
    val enterTipLevel: String?,
    @SerialName("ex_rate")
    val exRate: String?,
    @SerialName("faker_data_rate")
    val fakerDataRate: Int?,
    @SerialName("fps")
    val fps: String?,
    @SerialName("fuckactivity")
    val fuckactivity: Boolean?,
    @SerialName("id")
    val id: String?,
    @SerialName("ios_shelves")
    val iosShelves: String?,
    @SerialName("ipa_des")
    val ipaDes: String?,
    @SerialName("ipa_ewm")
    val ipaEwm: String?,
    @SerialName("ipa_url")
    val ipaUrl: String?,
    @SerialName("ipa_ver")
    val ipaVer: String?,
    @SerialName("keyframe")
    val keyframe: String?,
    @SerialName("level")
    val level: List<Level?>?,
    @SerialName("levelanchor")
    val levelanchor: List<Levelanchor?>?,
    @SerialName("live_bitrate")
    val liveBitrate: Int?,
    @SerialName("live_height")
    val liveHeight: String?,
    @SerialName("live_resolution")
    val liveResolution: LiveResolution?,
    @SerialName("live_time_coin")
    val liveTimeCoin: List<Int?>?,
    @SerialName("live_type")
    val liveType: List<List<String?>?>?,
    @SerialName("live_width")
    val liveWidth: String?,
    @SerialName("liveclass")
    val liveclass: List<Liveclas?>?,
    @SerialName("lotterybase")
    val lotterybase: String?,
    @SerialName("maintain_switch")
    val maintainSwitch: String?,
    @SerialName("maintain_tips")
    val maintainTips: String?,
    @SerialName("mobile")
    val mobile: String?,
    @SerialName("name_coin")
    val nameCoin: Boolean?,
    @SerialName("name_votes")
    val nameVotes: String?,
    @SerialName("op_domain")
    val opDomain: Boolean?,
    @SerialName("openGameShield")
    val openGameShield: Boolean?,
    @SerialName("open_stream_encrypt")
    val openStreamEncrypt: Boolean?,
    @SerialName("pub_msg")
    val pubMsg: String?,
    @SerialName("qiniu_domain")
    val qiniuDomain: String?,
    @SerialName("qq_desc")
    val qqDesc: String?,
    @SerialName("qq_icon")
    val qqIcon: String?,
    @SerialName("qq_title")
    val qqTitle: String?,
    @SerialName("qq_url")
    val qqUrl: String?,
    @SerialName("qr_url")
    val qrUrl: String?,
    @SerialName("quality")
    val quality: String?,
    @SerialName("region_exchange_info")
    val regionExchangeInfo: RegionExchangeInfo?,
    @SerialName("share_des")
    val shareDes: String?,
    @SerialName("share_title")
    val shareTitle: String?,
    @SerialName("show_lottery_profit_rank")
    val showLotteryProfitRank: Boolean?,
    @SerialName("sina_desc")
    val sinaDesc: String?,
    @SerialName("sina_icon")
    val sinaIcon: String?,
    @SerialName("sina_title")
    val sinaTitle: String?,
    @SerialName("sina_url")
    val sinaUrl: String?,
    @SerialName("site")
    val site: String?,
    @SerialName("sitename")
    val sitename: String?,
    @SerialName("sprout_eye")
    val sproutEye: String?,
    @SerialName("sprout_face")
    val sproutFace: String?,
    @SerialName("sprout_key")
    val sproutKey: String?,
    @SerialName("sprout_pink")
    val sproutPink: String?,
    @SerialName("sprout_saturated")
    val sproutSaturated: String?,
    @SerialName("sprout_skin")
    val sproutSkin: String?,
    @SerialName("sprout_white")
    val sproutWhite: String?,
    @SerialName("txcloud_appid")
    val txcloudAppid: String?,
    @SerialName("txcloud_bucket")
    val txcloudBucket: String?,
    @SerialName("txcloud_region")
    val txcloudRegion: String?,
    @SerialName("tximgfolder")
    val tximgfolder: String?,
    @SerialName("txvideofolder")
    val txvideofolder: String?,
    @SerialName("video_audit_switch")
    val videoAuditSwitch: String?,
    @SerialName("video_share_des")
    val videoShareDes: String?,
    @SerialName("video_share_title")
    val videoShareTitle: String?,
    @SerialName("wechat_ewm")
    val wechatEwm: String?,
    @SerialName("wx_siteurl")
    val wxSiteurl: String?,
//    @SerialName("decrypt_sdk_key")
//    val decryptSdkKey: Any?,
//    @SerialName("share_type")
//    val shareType: List<Any?>?,
//    @SerialName("ExtensionPage")
//    val extensionPage: List<Any?>?,
//    @SerialName("login_type")
//    val loginType: List<Any?>?,
)