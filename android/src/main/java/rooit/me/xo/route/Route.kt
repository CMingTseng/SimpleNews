package rooit.me.xo.route

import androidx.annotation.IdRes
import rooit.me.xo.R

sealed class Route(@IdRes val id: Int, val routeName: String, val deepLinkUri: String) {
    data object Splash : Route(R.id.splash_step1, "PageSplash", "app://splash")
    data object Login : Route(R.id.login_step1, "PageLogin" ,"app://login")
    data object News : Route(R.id.navigation_news, "PageNews", "app://news")
    companion object {
        const val SPLASH_REQUEST_KEY = "splash_request_key"
        const val LOGIN_REQUEST_KEY = "login_request_key"

        const val ARGS_KEY = "args_content"
    }
}