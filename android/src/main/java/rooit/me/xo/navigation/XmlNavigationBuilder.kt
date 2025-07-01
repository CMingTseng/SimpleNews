package rooit.me.xo.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraph
import rooit.me.xo.route.Route

class XmlNavigationBuilder : NavigationBuilder {
    override fun build(
        navController: NavController,
        graphResId: Int,
        startDestId: Route
    ): NavGraph {
        val navGraph = navController.navInflater.inflate(graphResId)
        navGraph.setStartDestination(startDestId.id)
        return navGraph
    }
}