package rooit.me.xo.utils.http.interceptor.live

//Ref : https://github.com/ktorio/ktor-documentation/blob/2.3.10/codeSnippets/snippets/client-custom-plugin-auth/src/main/kotlin/com/example/plugins/Auth.kt
//Ref : https://ktor.io/docs/client-custom-plugins.html#authentication
import io.ktor.client.plugins.api.ClientPlugin
import io.ktor.client.plugins.api.Send
import io.ktor.client.plugins.api.createClientPlugin
import io.ktor.http.HttpStatusCode

public val AuthPlugin: ClientPlugin<AuthPluginConfig> =
    createClientPlugin("AuthPlugin", ::AuthPluginConfig) {
        val token = pluginConfig.token //FIXME token <--- pluginConfig <---token provider (DI)

        on(Send) { request ->
            val originalCall = proceed(request)
            originalCall.response.run { // this: HttpResponse
                if (status == HttpStatusCode.Unauthorized && headers["WWW-Authenticate"]!!.contains(
                        "Bearer"
                    )
                ) {
                    request.headers.append("Authorization", "Bearer $token")
                    proceed(request)
                } else {
                    originalCall
                }
            }
        }
    }

public class AuthPluginConfig {
    public var token: String = ""
}