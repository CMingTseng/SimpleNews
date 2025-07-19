package rooit.me.xo.navigation

import androidx.navigation.NavController
import rooit.me.xo.route.Route

interface NavGraphContentLambdaBuilder {
    fun defineGraphContent(
        navController: NavController,
        startDestId: Route,
        args_string: String? = null,
        graphResId: Int? = null
    ): androidx.navigation.NavGraphBuilder.() -> Unit
}