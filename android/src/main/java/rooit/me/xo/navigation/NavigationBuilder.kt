package rooit.me.xo.navigation

import androidx.annotation.NavigationRes
import androidx.navigation.NavController
import androidx.navigation.NavGraph

interface NavigationBuilder {
    fun build(navController: NavController, @NavigationRes graphResId: Int, startDestId: Int): NavGraph
}