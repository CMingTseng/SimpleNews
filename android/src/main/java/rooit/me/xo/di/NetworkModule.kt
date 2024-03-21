package rooit.me.xo.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.core.scope.Scope
import org.koin.dsl.module
import retrofit2.Retrofit
import rooit.me.xo.BuildConfig
import java.util.concurrent.TimeUnit

private const val CONNECT_TIMEOUT = 15L
private const val WRITE_TIMEOUT = 15L
private const val READ_TIMEOUT = 15L
private val contentType = "application/json"

val JSON = Json {
    coerceInputValues = true
    ignoreUnknownKeys = true
    useAlternativeNames = false
}

object NetworkModule {
    val RetrofitModule = module {
        single { Cache(androidApplication().cacheDir, 10L * 1024 * 1024) }
        single { retrofitHttpClient() }
        single { retrofitBuilder() }
    }

//Ref : https://blog.csdn.net/jingzz1/article/details/120646631
//Ref : https://github.com/JakeWharton/retrofit2-kotlinx-serialization-converter/issues/58
    private fun Scope.retrofitBuilder(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(JSON.asConverterFactory(contentType.toMediaType()))
            .client(get())
            .build()
    }

    private fun Scope.retrofitHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().apply {
            cache(get())
            connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
            readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            retryOnConnectionFailure(true)
            addInterceptor(HttpLoggingInterceptor().apply {
                level = if (BuildConfig.DEBUG) {
                    HttpLoggingInterceptor.Level.BODY
                } else {
                    HttpLoggingInterceptor.Level.NONE
                }
            })
            addInterceptor { chain ->
                chain.proceed(chain.request().newBuilder().apply {
                    header("Accept", contentType)
                }.build())
            }
        }.build()
    }
}