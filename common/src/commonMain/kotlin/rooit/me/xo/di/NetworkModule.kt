package rooit.me.xo.di

import de.jensklingenberg.ktorfit.Ktorfit
import de.jensklingenberg.ktorfit.converter.builtin.CallConverterFactory
import de.jensklingenberg.ktorfit.converter.builtin.FlowConverterFactory
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.module.Module
import org.koin.core.scope.Scope
import org.koin.dsl.module
import io.ktor.client.call.body
import io.ktor.client.plugins.HttpClientPlugin
import io.ktor.client.plugins.HttpSend
import io.ktor.client.plugins.api.createClientPlugin
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.plugin
import io.ktor.client.request.HttpRequestPipeline
import io.ktor.client.request.get
import io.ktor.client.request.url
import io.ktor.client.statement.HttpResponseContainer
import io.ktor.client.statement.HttpResponsePipeline
import io.ktor.http.ParametersBuilder
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import io.ktor.serialization.suitableCharset
import io.ktor.util.AttributeKey
import io.ktor.util.KtorDsl
import rooit.me.xo.utils.log.LogUtils

public val JSON: Json = Json {
    coerceInputValues = true
    ignoreUnknownKeys = true
    useAlternativeNames = false
    isLenient = true
}

public object NetworkModule {
    public val KtorfitModule: Module = module {
        single { ktorfitBuilder(get()) }
    }
    private fun Scope.ktorfitBuilder(baseUrl: String ): Ktorfit {
        return Ktorfit.Builder().apply {
            baseUrl(baseUrl)
            httpClient(HttpClient {
                install(ContentNegotiation) {
                    json(JSON)
                }
                install(Logging) {
                    logger = object : Logger {
                        override fun log(message: String) {
                            LogUtils.info("http", message)
                        }
                    }
                    level = LogLevel.ALL
                }
            })
            converterFactories(
                FlowConverterFactory(),
                CallConverterFactory(),
            )
        }.build()
    }
}