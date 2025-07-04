package rooit.me.xo.navigation

import androidx.annotation.NavigationRes
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import rooit.me.xo.route.Route

interface NavigationBuilder {
    fun build(navController: NavController, startDestId: Route, @NavigationRes graphResId: Int?=null): NavGraph
}