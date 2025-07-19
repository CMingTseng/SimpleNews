package rooit.me.xo.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraph
import rooit.me.xo.route.Route

interface NavigationBuilder {
    fun build(navController: NavController, startDest: Route, graphResId: Int?=null): NavGraph
}