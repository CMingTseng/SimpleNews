package rooit.me.xo.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraph

class XmlNavigationBuilder : NavigationBuilder {
    override fun build(
        navController: NavController,
        graphResId: Int,
        startDestId: Int
    ): NavGraph {
        val navGraph = navController.navInflater.inflate(graphResId)
        navGraph.setStartDestination(startDestId)
        return navGraph
    }
}