package rooit.me.xo.api.live

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public open class BaseRespBean<T> {
    @SerialName("ret")
    public val ret: Int = -1

    @SerialName("data")
    public val data: T? = null

    @SerialName("msg")
    public val msg: String? = null

    public fun onSuccess(block: (data: T?) -> Unit) {
        if (ret == 200) {
            block.invoke(data)
        }
    }

    public fun onFailure(block: (msg: String?) -> Unit) {
        if (ret != 0) {
            block.invoke(msg)
        }
    }
}