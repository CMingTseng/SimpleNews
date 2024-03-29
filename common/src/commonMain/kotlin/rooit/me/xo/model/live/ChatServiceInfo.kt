package rooit.me.xo.model.live

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class ChatServiceInfo (
    @SerialName("chat_service_url")
    val chat_service_url: String?
)