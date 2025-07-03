package rooit.me.xo.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.createGraph
import rooit.me.xo.route.Route
import androidx.navigation.fragment.fragment
import rooit.me.xo.R
import rooit.me.xo.ui.login.PageLogin
import rooit.me.xo.ui.news.PageNews
import rooit.me.xo.ui.splash.PageSplash

class KDslNavigationBuilder : NavigationBuilder {
    override fun build(
        navController: NavController,
        startDestId: Route,
        graphResId: Int?
    ): NavGraph {
        return navController.createGraph(
            id = R.id.main_nav_graph,
            startDestination = startDestId.id
        ) {
            fragment<PageSplash>(Route.Splash.id) {
                label = "SplashPage"
            }
            fragment<PageLogin>(Route.Login.id) {
                label = "LoginPage"
            }
            fragment<PageNews>(Route.News.id) {
                label = "NewsPage"
            }
        }
    }
}