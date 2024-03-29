package rooit.me.xo.model.live

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class SettingPreferences(
    @SerialName("href")
    val href: String?,
    @SerialName("id")
    val id: String?,
    @SerialName("name")
    val name: String?,
    @SerialName("thumb")
    val thumb: String?
)