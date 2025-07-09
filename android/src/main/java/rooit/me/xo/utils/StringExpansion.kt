package rooit.me.xo.utils

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject

fun String.toMap(): Map<String, Any> = runCatching {
    if (this.isBlank()) return@runCatching emptyMap()
    val jsonElement = Json.Default.parseToJsonElement(this)
    if (jsonElement is JsonObject) {
        jsonElement.toMapRecursive()
    } else {
        emptyMap()
    }
}.getOrElse {
    emptyMap()
}
