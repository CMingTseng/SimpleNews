package rooit.me.xo.utils

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Parcelable
import androidx.core.net.toUri
import androidx.navigation.*
import timber.log.Timber

//Ref : https://stackoverflow.com/questions/65610003/pass-parcelable-argument-with-compose-navigation/68847035#68847035
//Ref : https://stackoverflow.com/questions/68747549/jetpack-compose-navigation-with-multiple-optional-argments
@SuppressLint("RestrictedApi")
fun NavController.navigate(
    route: String,
    args: Bundle,
    navOptions: NavOptions? = null,
    navigatorExtras: Navigator.Extras? = null
) {
    val navdestinationroute=NavDestination.createRoute(route)
    val navdestinationrouteUri=navdestinationroute.toUri()
    val routeLink = NavDeepLinkRequest
        .Builder
        .fromUri(navdestinationrouteUri)
        .build()
//    Timber.e("Show me NavDestination route ${navdestinationroute} \n uri $navdestinationrouteUri \n routeLink $routeLink")
    val deepLinkMatch = graph.matchDeepLink(routeLink)
//    Timber.d("--- Printing NavGraph Details ---")
//    printNavGraphDetails(this.graph) // 打印整個導航圖
//    Timber.d("--- End of NavGraph Details ---")
    if (deepLinkMatch != null) {
        val destination = deepLinkMatch.destination
        val id = destination.id
        navigate(id, args, navOptions, navigatorExtras)
    } else {
        navigate(route, navOptions, navigatorExtras)
    }
}

private fun printNavGraphDetails(navGraph: NavGraph, indent: String = "") {
    Timber.d("${indent}Graph ID: ${navGraph.id}, Route: ${navGraph.route}, DisplayName: ${navGraph.displayName}")
    navGraph.forEach { destination ->
        Timber.d("${indent}  Destination ID: ${destination.id}, Route: ${destination.route}, DisplayName: ${destination.displayName}, Navigator: ${destination.navigatorName}")
        if (destination is NavGraph) {
            printNavGraphDetails(destination, "$indent    ") // 遞迴打印子圖
        } else {
            // 打印 NavDestination 的 Deep Links
            // NavDestination 內部有一個 deepLinks 列表，但它不是直接公開的
            // 我們可以嘗試通過 NavDestination 的內容來推斷，或者如果需要更精確的，可能要用反射（不推薦用於生產）
            // 一個間接的方式是檢查它的 arguments 是否暗示了 deep link 的參數
            // 但最直接的是檢查 NavDestination 的 mDeepLinks (私有字段) 或類似結構
            // 為了簡化，我們先打印 route，因為 route 本身就可以作為一種內部 deep link
            // 如果你的 deepLink URI 就是基於 route 構建的，那看 route 就夠了。
            // 實際上，NavDestination 有一個 `getDeepLinks()` 方法，返回 `List<NavDeepLink>`
            // 但 `NavDeepLink` 類的 `getUriPattern()` 也不是直接 public。
            // 我們可以嘗試迭代 arguments，因為 deep link 參數會成為 argument

            // 更好的方法：直接訪問 deepLinks 列表 (如果 NavController 的 API 允許)
            // 在 NavDestination 中，deepLinks 是以 List<NavDeepLink> 的形式存在的，
            // 即使 NavDeepLink 的 uriPattern 不是 public，我們至少可以看到有沒有 deepLink。

            // 由於 NavDestination.deepLinks 是 internal，我們不能直接在擴展函數中遍歷它。
            // 所以我們只能基於已有的公共 API。

            // 我們可以這樣檢查：如果一個 destination 能匹配一個特定的 deep link URI，
            // 那它就隱含地 "支持" 這個 deep link。
            // 但這無助於我們 "列出" 它們。

            // 讓我們專注於我們關心的那個 URI
            val expectedDeepLinkUri = "android-app://androidx.navigation/PageLogin".toUri()
            val requestForDebug = NavDeepLinkRequest.Builder.fromUri(expectedDeepLinkUri).build()

            // 嘗試手動為每個 destination 執行 matchDeepLink (這比較繁瑣)
            // val matchForThisDest = destination.matchDeepLink(requestForDebug) // NavDestination 沒有直接的 matchDeepLink，是 NavGraph 的
            // 更簡單的方式是，如果我們在 NavGraph XML 中手動添加了 <deepLink app:uri="android-app://androidx.navigation/PageLogin" />
            // 那麼當外層的 graph.matchDeepLink 執行時就應該能找到。

            // 既然我們無法輕易列出 destination 的所有 deepLink URIs，
            // 最好的辦法還是去檢查你的 nav_graph.xml 文件。
        }
    }
}

////fail : currentBackStackEntry not now/current new destination
//fun NavController.navigate(route: String, vararg args: Pair<String, Parcelable>) {
//    navigate(route)
//    requireNotNull(currentBackStackEntry?.arguments).apply {
//        args.forEach { (key: String, arg: Parcelable) ->
//            putParcelable(key, arg)
//        }
//    }
//}

inline fun <reified T : Parcelable> NavBackStackEntry.requiredArg(key: String): T {
    return requireNotNull(arguments) { "arguments bundle is null" }.run {
        requireNotNull(getParcelable(key)) { "argument for $key is null" }
    }
}