package rooit.me.xo.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraph
import rooit.me.xo.route.Route

class XmlNavigationBuilder : NavigationBuilder {
    override fun build(
        navController: NavController,
        startDestId: Route,
        graphResId: Int?,
    ): NavGraph {
        val navGraph = navController.navInflater.inflate(graphResId!!)
        navGraph.setStartDestination(startDestId.id)
        return navGraph
    }
}