package rooit.me.xo.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public open class BaseDataBean<T> {
    @SerialName("code")
    public val code: Int = -1//TODO Ref ErrorCode

    @SerialName("info")
    public val info: List<T>? = null

    @SerialName("msg")
    public val msg: String? = null
}