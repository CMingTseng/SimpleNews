package rooit.me.xo.navigation

import androidx.annotation.NavigationRes
import androidx.navigation.NavController
import rooit.me.xo.route.Route

interface NavGraphContentLambdaBuilder {
    fun defineGraphContent(
        navController: NavController,
        startDestId: Route,
        args_string: String? = null,
        @NavigationRes graphResId: Int? = null
    ): androidx.navigation.NavGraphBuilder.() -> Unit
}