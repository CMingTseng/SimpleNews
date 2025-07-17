package rooit.me.xo.di

import de.jensklingenberg.ktorfit.Ktorfit
import de.jensklingenberg.ktorfit.converter.CallConverterFactory
import de.jensklingenberg.ktorfit.converter.FlowConverterFactory
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.qualifier.named
import org.koin.dsl.module

private const val CONNECT_TIMEOUT = 15L
private const val WRITE_TIMEOUT = 15L
private const val READ_TIMEOUT = 15L
private val contentType = "application/json"

val JSON = Json {
    coerceInputValues = true
    ignoreUnknownKeys = true
    useAlternativeNames = false
}

val NetworkModule = module {
    single { ktorfitBuilder(get(named("API_URL"))) }
}

//Ref : https://blog.csdn.net/jingzz1/article/details/120646631
//Ref : https://github.com/JakeWharton/retrofit2-kotlinx-serialization-converter/issues/58

private fun ktorfitBuilder(api_url: String ): Ktorfit {
    println("Show me  api_url $api_url")
    return Ktorfit.Builder().apply {
        baseUrl(api_url)
        httpClient(HttpClient(CIO) {
            install(ContentNegotiation) {
                json(JSON)
            }
            install(HttpTimeout) {
                requestTimeoutMillis = 15000L
                connectTimeoutMillis = 15000L
                socketTimeoutMillis = 15000L
            }
            install(Logging) {
                logger = Logger.DEFAULT
                level = LogLevel.ALL

            }
        })
        converterFactories(
            FlowConverterFactory(),
            CallConverterFactory(),
        )
    }.build()
}