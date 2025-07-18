package rooit.me.xo.navigation

import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.createGraph
import androidx.navigation.fragment.fragment
import androidx.navigation.navArgument
import rooit.me.xo.route.Route
import rooit.me.xo.route.Route.Companion.ARGS_KEY
import rooit.me.xo.ui.login.PageLogin
import rooit.me.xo.ui.news.PageNews
import rooit.me.xo.ui.splash.PageSplash

class KDslNavigationBuilder : NavGraphContentLambdaBuilder, NavigationBuilder {
    override fun defineGraphContent(
        navController: NavController,
        startDestId: Route,
        args_string: String?,
        graphResId: Int?
    ): NavGraphBuilder.() -> Unit {
        return {
            //Ref : FragmentNavigatorDestinationBuilder  like  : fun createFragmentDestinationConfig( ): FragmentNavigatorDestinationBuilder.() -> Unit {}
            fragment<PageSplash>(startDestId.routeName) { // 使用传入的 startDestId
                label = "Splash"
                deepLink { uriPattern = Route.Splash.deepLinkUri } // 示例
                deepLink { uriPattern = NavDestination.createRoute(Route.Splash.routeName) }
            }

//            fragment<PageSplash>(Route.Splash.id) {//Int type
//                label = "Splash" // 或者直接 "Splash"
//                deepLink { uriPattern = Route.Splash.deepLinkUri }
//            }

            fragment<PageLogin>("${Route.Login.routeName}/{${args_string}}?") {
                label = "Login"
                argument(ARGS_KEY) {
                    type = androidx.navigation.NavType.StringType
                    nullable = true
                }
                deepLink { uriPattern = Route.Login.deepLinkUri } // 示例
                deepLink { uriPattern = NavDestination.createRoute(Route.Login.routeName) }
//                action(0x9999) {
//                    destinationId = R.id.xxxxx
//                    navOptions {
//                        anim {
//                            enter = R.anim.slide_in_right
//                            exit = R.anim.slide_out_left
//                            popEnter = R.anim.slide_in_left
//                            popExit = R.anim.slide_out_right
//                        }
//                        popUpTo(Route.Login.routeName) {
////                        popUpTo(R.id.cccccc) {
//                            inclusive = true
//                        }
//                        launchSingleTop = true // 如果目标已在栈顶，则不重新创建
//                    }
//                    defaultArguments.put("key1", "value")
//                }
            }

            fragment<PageNews>("${Route.News.routeName}/{${args_string}}?") {
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
        startDest: Route,
        graphResId: Int?
    ): NavGraph {
//        return navController.createGraph(//TODO direct use defineGraphContent
//            startDestination = startDest.routeName,
//            route = "all_graph",
//            builder = defineGraphContent(navController, startDest, null, graphResId) )
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