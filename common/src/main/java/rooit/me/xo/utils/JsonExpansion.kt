package rooit.me.xo.utils

import kotlinx.serialization.json.*

fun JsonObject.toMapRecursive(): Map<String, Any> {
    return this.mapValues { (_, jsonElement) ->
        convertJsonElementToNative(jsonElement)
    }
}

private fun convertJsonElementToNative(jsonElement: JsonElement): Any {
    return when (jsonElement) {
        is JsonObject -> jsonElement.toMapRecursive() // Recursively convert nested objects
        is JsonArray -> jsonElement.map { convertJsonElementToNative(it) } // Convert arrays to List<Any>
        is JsonPrimitive -> {
            when {
                jsonElement.isString -> jsonElement.content
                jsonElement == JsonNull -> Any() // Or represent null differently if needed
                else -> {
                    // Attempt to infer primitive type (Boolean, Long, Double)
                    // This is a simplified inference. For robust type handling,
                    // especially with numbers, more specific checks might be needed.
                    jsonElement.content.toBooleanStrictOrNull()
                        ?: jsonElement.content.toLongOrNull()
                        ?: jsonElement.content.toDoubleOrNull()
                        ?: jsonElement.content // Fallback to string if no other type matches
                }
            }
        }
    }
}
