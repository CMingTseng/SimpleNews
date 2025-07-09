package rooit.me.xo.utils

import android.net.Uri
import android.os.Bundle
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.add
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import kotlinx.serialization.json.putJsonArray

fun Bundle.toQueryString(): String {
    return keySet()
        .joinToString("&") { key ->
            val value = get(key)
            "$key=${Uri.encode(value?.toString())}"
        }
}

fun Bundle.toJsonString(): String {
    val json = buildJsonObject {
        keySet().forEach { key ->
            when (val value = get(key)) {
                is Int -> put(key, value)
                is String -> put(key, value)
                is Boolean -> put(key, value)
                is Long -> put(key, value)
                is Double -> put(key, value)
                is Float -> put(key, value.toDouble())
                is ArrayList<*> -> {
                    putJsonArray(key) {
                        value.forEach { element ->
                            if (element is String) add(element)
                        }
                    }
                }
                else -> {
                    // 其他型態可再擴充
                    put(key, value.toString())
                }
            }
        }
    }
    return Json.encodeToString(JsonObject.serializer(), json)
}

fun Bundle.toUriJsonString(): String {
    val json = buildJsonObject {
        keySet().forEach { key ->
            when (val value = get(key)) {
                is Int -> put(key, value)
                is String -> put(key, value)
                is Boolean -> put(key, value)
                is Long -> put(key, value)
                is Double -> put(key, value)
                is Float -> put(key, value.toDouble())
                is ArrayList<*> -> {
                    putJsonArray(key) {
                        value.forEach { element ->
                            if (element is String) add(element)
                        }
                    }
                }
                else -> {
                    // 其他型態可再擴充
                    put(key, value.toString())
                }
            }
        }
    }
    return Uri.encode(Json.encodeToString(JsonObject.serializer(), json))
}