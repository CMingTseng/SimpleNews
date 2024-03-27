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
            })
            converterFactories(
                FlowConverterFactory(),
                CallConverterFactory(),
            )
        }.build()
    }
}