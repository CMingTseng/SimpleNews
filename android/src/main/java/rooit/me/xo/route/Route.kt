package rooit.me.xo.route

import androidx.annotation.IdRes
import rooit.me.xo.R

sealed class Route(@IdRes val id: Int, val routeName: String) {
    data object Splash : Route(R.id.splash_step1, "PageSplash")

    data object News : Route(R.id.navigation_news, "PageLogin")
    companion object {
        const val SPLASH_REQUEST_KEY = "splash_request_key"
    }
}