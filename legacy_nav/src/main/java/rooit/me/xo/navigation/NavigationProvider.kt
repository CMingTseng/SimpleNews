package rooit.me.xo.navigation

object NavigationProvider {
    fun getBuilder(): NavigationBuilder {
//        return XmlNavigationBuilder()
        return KDslNavigationBuilder()
    }
}