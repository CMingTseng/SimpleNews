package rooit.me.xo.navigation

import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph
import androidx.navigation.NavType
import androidx.navigation.createGraph
import rooit.me.xo.route.Route
import androidx.navigation.fragment.fragment
import androidx.navigation.navArgument
import rooit.me.xo.route.Route.Companion.ARGS_KEY
import rooit.me.xo.ui.login.PageLogin
import rooit.me.xo.ui.news.PageNews
import rooit.me.xo.ui.splash.PageSplash
import androidx.navigation.NavGraphBuilder as AndroidXNavGraphBuilder

class KDslNavigationBuilder :  NavGraphContentLambdaBuilder,NavigationBuilder {
    override fun defineGraphContent(
        navController: NavController,
        startDestId: Route,
        graphResId: Int?
    ): AndroidXNavGraphBuilder.() -> Unit {
        return {
            fragment<PageSplash>(startDestId.routeName) { // 使用传入的 startDestId
                label = "Splash"
                deepLink { uriPattern = Route.Splash.deepLinkUri } // 示例
                deepLink { uriPattern = NavDestination.createRoute(Route.Splash.routeName) }
            }

            fragment<PageLogin>("${Route.Login.routeName}/{${ARGS_KEY}}?") {
                label = "Login"
                argument(ARGS_KEY) {
                    type = androidx.navigation.NavType.StringType
                    nullable = true
                }
                deepLink { uriPattern = Route.Login.deepLinkUri } // 示例
                deepLink { uriPattern = NavDestination.createRoute(Route.Login.routeName) }
            }

            fragment<PageNews>("${Route.News.routeName}/{${ARGS_KEY}}?") {
                label = "NewsPage"
                argument(ARGS_KEY) {
                    type = androidx.navigation.NavType.StringType
                    nullable = true
                }
                deepLink { uriPattern = Route.News.deepLinkUri } // 示例
                deepLink { uriPattern = NavDestination.createRoute(Route.News.routeName) }
            }
        }
    }

    override fun build(
        navController: NavController,
        startDestId: Route,
        graphResId: Int?
    ): NavGraph {
        return navController.createGraph(
            startDestination = Route.Splash.routeName,
            route = "all_graph"
        ) {
            fragment<PageSplash>(Route.Splash.routeName) {
                label = "Splash"
                // 支持外部 app scheme
                deepLink { uriPattern = Route.Splash.deepLinkUri }
                // 支持內部 createRoute 生成的 URI (用於你的擴展函數)
                deepLink { uriPattern = NavDestination.createRoute(Route.Splash.routeName) }
            }
            // route 總是帶有可選的 args_content 參數:
            // fragment<PageLogin>("${Route.Login.routeName}/{${Route.ARGS_KEY}}?") { // '?' 使其可選
            // 或者如果 args_content 不是 route 的一部分，而是通過 bundle 傳遞
            fragment<PageLogin>("${Route.Login.routeName}/{${Route.ARGS_KEY}}?") {
                label = "Login"
                listOf(
                    navArgument(ARGS_KEY) {
                        type = NavType.StringType
                        nullable = true
                    }
                )
                // 支持外部 app scheme
                deepLink { uriPattern = Route.Login.deepLinkUri }
                // 支持內部 createRoute 生成的 URI
                deepLink { uriPattern = NavDestination.createRoute(Route.Login.routeName) }
            }
            fragment<PageNews>("${Route.News.routeName}/{${Route.ARGS_KEY}}?") {
                label = "NewsPage"
                argument(ARGS_KEY) {
                    type = NavType.StringType
                    nullable = true
                }
                // 支持外部 app scheme
                deepLink { uriPattern = Route.News.deepLinkUri }
                // 支持內部 createRoute 生成的 URI
                deepLink { uriPattern = NavDestination.createRoute(Route.News.routeName) }
            }
        }
    }
}