package rooit.me.xo.di

import de.jensklingenberg.ktorfit.Ktorfit
import de.jensklingenberg.ktorfit.converter.builtin.CallConverterFactory
import de.jensklingenberg.ktorfit.converter.builtin.FlowConverterFactory
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.module.Module
import org.koin.core.scope.Scope
import org.koin.dsl.module
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
            val client: HttpClient = HttpClient {
                install(ContentNegotiation) {
                    json(JSON)
                }
                install(Logging) { //<---- Ktor HttpClientPlugin like okhttp Interceptor
                    logger = object : Logger {
                        override fun log(message: String) {
                            LogUtils.info("http", message)
                        }
                    }
                    level = LogLevel.ALL
                }
            }
            httpClient(client)
            converterFactories(
                FlowConverterFactory(),
                CallConverterFactory(),
            )
        }.build()
    }


//        public val interceptors: Module = module {
//            single { ClientPlugin(*) }
//        }
}