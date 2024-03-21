package rooit.me.xo.di

import de.jensklingenberg.ktorfit.Ktorfit
import de.jensklingenberg.ktorfit.converter.builtin.CallConverterFactory
import de.jensklingenberg.ktorfit.converter.builtin.FlowConverterFactory
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.scope.Scope
import org.koin.dsl.module
import rooit.me.xo.BuildConfig

val JSON = Json {
    coerceInputValues = true
    ignoreUnknownKeys = true
    useAlternativeNames = false
    isLenient = true
}

object NetworkModule {
    val KtorfitModule = module {
        single { ktorfitBuilder() }
    }

    private fun Scope.ktorfitBuilder(): Ktorfit {
        return Ktorfit.Builder().apply {
            baseUrl(BuildConfig.BASE_URL)
            httpClient(HttpClient {
                install(ContentNegotiation) {
                    json(JSON)
                }
            })
            converterFactories(
                FlowConverterFactory(),
                CallConverterFactory(),
            )
        }.build()
    }
}