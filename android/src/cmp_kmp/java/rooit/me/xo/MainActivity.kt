package rooit.me.xo

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.core.bundle.bundleOf
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavDestination
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import kotlinx.coroutines.flow.StateFlow
import rooit.me.xo.route.Route
import rooit.me.xo.route.Route.Companion.ARGS_KEY
import rooit.me.xo.ui.login.PageLogin
import rooit.me.xo.ui.news.PageNews
import rooit.me.xo.ui.splash.PageSplash
import rooit.me.xo.utils.toJsonString
import rooit.me.xo.utils.toUriJsonString
import timber.log.Timber

class MainActivity : AppCompatActivity() {
    companion object {
        const val NEWS_ITEM_CLICKED_TITLE_KEY = "news_item_title_key"

        const val NO_DATA_RETURNED = "no_data_from_news_yet"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        var args_string=""
        intent?.extras?.let { bundle ->
            args_string = bundle.toUriJsonString()
        }
        setContent {
            val navController = rememberNavController()
            Scaffold(
                modifier = Modifier
                    .safeContentPadding()
                    .fillMaxSize()
            ) { innerPadding ->
                NavHost(
                    navController = navController,
                    startDestination = "${Route.Splash.routeName}/${args_string}",
                    modifier = Modifier.padding(innerPadding)
                ) {
                    composable(
                        route = "${Route.Splash.routeName}/{${Route.ARGS_KEY}}?",
                        arguments = listOf(
                            navArgument(Route.ARGS_KEY) { // 使用 Route.ARGS_KEY
                                type = NavType.StringType
                                nullable = true
                                defaultValue = null
                            }
                        ),
                        deepLinks = listOf(
                            navDeepLink { uriPattern = Route.Splash.deepLinkUri },
                            navDeepLink { uriPattern = NavDestination.createRoute(Route.Splash.routeName) }
                        )
                    ) { backStackEntry ->
                        val receivedJsonArgs = backStackEntry.arguments?.getString(Route.ARGS_KEY)
                        PageSplash(
                            onNavigateToLogin = { result->
                                navController.navigate("${Route.Login.routeName}/${result}") {
//                                    popUpTo("${Route.Splash.routeName}/") { inclusive = true }
                                    navController.graph.startDestinationRoute?.let { startRoute ->
                                        popUpTo(startRoute) {
                                            inclusive = true
                                        }
                                    }
                                    launchSingleTop = true
                                }
                            },
                            onNavigateToNews = {result->
                                navController.navigate("${Route.News.routeName}/${result}") {
                                    popUpTo("${Route.Splash.routeName}/${args_string}") { inclusive = true }
//                                    navController.graph.startDestinationRoute?.let { startRoute ->
//                                        popUpTo(startRoute) {
//                                            inclusive = true
//                                        }
//                                    }
                                    launchSingleTop = true
                                }
                            },
                            args=receivedJsonArgs
                        )
                    }
                    composable(
                        route = "${Route.Login.routeName}/{${Route.ARGS_KEY}}?",
                        arguments = listOf(
                            navArgument(Route.ARGS_KEY) { // 使用 Route.ARGS_KEY
                                type = NavType.StringType
                                nullable = true
                                defaultValue = null
                            }
                        ),
                        deepLinks = listOf(
                            navDeepLink { uriPattern = Route.Login.deepLinkUri },
                            navDeepLink { uriPattern = NavDestination.createRoute(Route.Login.routeName) }
                        )
                    ) { backStackEntry ->
                        val receivedJsonArgs = backStackEntry.arguments?.getString(Route.ARGS_KEY)
                        val returnedNewsTitleFlow: StateFlow<String> = backStackEntry
                            .savedStateHandle
                            .getStateFlow(NEWS_ITEM_CLICKED_TITLE_KEY, NO_DATA_RETURNED) // 提供初始值

                        // collectAsStateWithLifecycle 會感知生命周期，更安全
                        val returnedNewsTitle by returnedNewsTitleFlow.collectAsStateWithLifecycle()
                        Timber.e("NavigationArgs  show returnedData : $returnedNewsTitle")
                        PageLogin(
                            onNavigateToNews = {result->
                                Timber.e("NavigationArgs  login  Processed: $result")
                                // 清除 SavedStateHandle 中的值，以便下次即使相同的值也能觸發更新
                                // 注意：getStateFlow 的一個行為特性是，如果鍵不存在，它會使用初始值創建並返回 Flow。
                                // remove() 後，下次 getStateFlow 仍會以 initialValue 重新開始。
                                backStackEntry.savedStateHandle.remove<String>(NEWS_ITEM_CLICKED_TITLE_KEY)
                                // 或者，如果想讓它回到 "無數據" 狀態，可以再次 set 成初始值：
                                // backStackEntry.savedStateHandle[NEWS_ITEM_CLICKED_TITLE_KEY] = NO_DATA_RETURNED
                                navController.navigate("${Route.News.routeName}/${result}") {
                                    popUpTo("${Route.Login.routeName}/${receivedJsonArgs}") {
                                        inclusive = true
                                    }
                                    launchSingleTop = true
                                }
                            },
                            args = receivedJsonArgs
                        )
                    }
                    composable(
                        route = "${Route.News.routeName}/{$ARGS_KEY}?",
                        arguments = listOf(
                            navArgument(ARGS_KEY) {
                                type = NavType.StringType
                                nullable = true
                                defaultValue = null
                            }
                        ),
                        deepLinks = listOf(
                            navDeepLink { uriPattern = Route.News.deepLinkUri },
                            navDeepLink { uriPattern = NavDestination.createRoute(Route.News.routeName) }
                        )
                    ) {backStackEntry ->
                        val receivedJsonArgs = backStackEntry.arguments?.getString(Route.ARGS_KEY)
                        PageNews(onNewsItemClickedAndPopBack = { newsTitle ->
                            navController.previousBackStackEntry
                                ?.savedStateHandle
                                ?.set(NEWS_ITEM_CLICKED_TITLE_KEY, newsTitle)
                            navController.popBackStack()
                        },args = receivedJsonArgs)
                    }
                }
            }
        }
    }
}
