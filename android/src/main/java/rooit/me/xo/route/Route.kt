package rooit.me.xo.route

sealed class Route(val routeName: String, val deepLinkUri: String) {
    val id: Int = routeName.hashCode()

    data object Splash : Route("PageSplash", "app://splash")
    data object Login : Route("PageLogin" ,"app://login")
    data object News : Route("PageNews", "app://news")
    companion object {
        const val SPLASH_REQUEST_KEY = "splash_request_key"
        const val LOGIN_REQUEST_KEY = "login_request_key"

        const val ARGS_KEY = "args_content"
    }
}