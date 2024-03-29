package rooit.me.xo.api.live

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public open class BaseDataBean<T> {
    @SerialName("code")
    public val code: Int = -1//TODO Ref ErrorCode

    @SerialName("info")//FIXME !! WTF User.getChatConfigInfo "info": {  "chat_service_url": ""}
    public val info: List<T>? = null

    @SerialName("msg")
    public val msg: String? = null
}