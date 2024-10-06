package rooit.me.xo

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.view.Window
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import rooit.me.xo.databinding.ActivityMainBinding
import rooit.me.xo.ui.flow.FlowStep
import rooit.me.xo.ui.flow.TAG_FLOW_STEP
import rooit.me.xo.utils.fragment.FragmentResultRequestKey
import timber.log.Timber
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavOptions
import androidx.navigation.createGraph
import androidx.navigation.fragment.fragment
import rooit.me.xo.def.TAG_ROUTE_FLOW_MAIN
import rooit.me.xo.def.TAG_ROUTE_FLOW_SPLASH
import rooit.me.xo.ui.flow.main.MainFlowFragment
import rooit.me.xo.ui.flow.splash.SplashFlowFragment

//Ref : https://developer.android.com/guide/navigation/design/kotlin-dsl
//Ref : https://developer.android.com/guide/navigation?hl=zh-tw#create_the_navhostfragment_programmatically
//Ref : https://stackoverflow.com/questions/67205861/kotlin-navigate-with-fragment-name-not-id
//Ref : https://developer.android.com/guide/navigation/design/global-action
//Ref : https://juejin.cn/post/6844903834758676488
//Ref : https://developer.android.com/guide/navigation/use-graph/programmatic
//Ref : https://juejin.cn/post/7103732683057938440
//Ref : https://blog.csdn.net/chuyouyinghe/article/details/135823864
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()
    private var exitTime: Long = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        window?.apply {
            requestFeature(Window.FEATURE_ACTION_BAR_OVERLAY)
            statusBarColor = Color.TRANSPARENT
            decorView?.let {
                it.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN)
            }
            //        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        }
        super.onCreate(savedInstanceState)
        installSplashScreen().apply {
            setKeepOnScreenCondition() {
                viewModel.isLoading.value
            }
        }
        if (intent.flags and Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT != 0) {
            finish()
            return
        }
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.root.setOnClickListener {
        }
        (supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment)?.apply {
            val mainNavController = this.navController
            mainNavController.apply {
                val defaultnavGraph =
                    createGraph(startDestination = TAG_ROUTE_FLOW_SPLASH) {
                        fragment<SplashFlowFragment>(route = TAG_ROUTE_FLOW_SPLASH) {
                            label = TAG_ROUTE_FLOW_SPLASH
                            action(R.id.flow_splash_init_fragment) {
                                destinationId = R.id.flow_splash_init_fragment
                                //Ref : https://www.cnblogs.com/guanxinjing/p/13878219.html
                                //Ref : https://blog.csdn.net/weixin_52173250/article/details/142391429
                                //Ref : https://blog.csdn.net/u012878270/article/details/130040578
                                //Ref : https://blog.csdn.net/stephen_sun_/article/details/123241134
                                //Ref : https://blog.csdn.net/ayong120/article/details/134402653
                                // 设置 Actions 的属性
                                NavOptions.Builder()
                                    .setPopUpTo(R.id.nav_flow_graph, true, false)
//                                    .setEnterAnim(R.anim.from_right) //进入动画
//                                    .setExitAnim(R.anim.to_left) //退出动画
//                                    .setPopEnterAnim(R.anim.to_left) //弹出进入动画
//                                    .setPopExitAnim(R.anim.from_right) //弹出退出动画
                                    .build()
                                defaultArguments.apply {
                                    TAG_FLOW_STEP to FlowStep.SPLASH.name
                                }
                            }
                        }
                        fragment<MainFlowFragment>(route = TAG_ROUTE_FLOW_MAIN) {
                            label = TAG_ROUTE_FLOW_MAIN
                            action(R.id.flow_main_fragment) {
                                destinationId = R.id.flow_main_fragment
                                NavOptions.Builder()
                                    .setPopUpTo(R.id.nav_flow_graph, true, false)
                                    .build()

                            }
                        }
                    }
                graph = defaultnavGraph
                navigate(route = TAG_ROUTE_FLOW_SPLASH)
            }

            childFragmentManager.setFragmentResultListener(
                FragmentResultRequestKey,
                this
            ) { requestkey, bundle ->
                if (requestkey == FragmentResultRequestKey) {
                    Timber.e("Show FragmentResult bundle $bundle")
                    bundle.getString(TAG_FLOW_STEP)?.let { flowstep ->
                        mainNavController.apply {
                            navigateUp()
                            val mainNavGraph =
                                createGraph(startDestination = TAG_ROUTE_FLOW_MAIN) {
                                    fragment<MainFlowFragment>(route = TAG_ROUTE_FLOW_MAIN) {
                                        label = TAG_ROUTE_FLOW_MAIN
                                        action(R.id.flow_main_fragment) {
                                            destinationId = R.id.flow_main_fragment
                                            NavOptions.Builder()
                                                .setPopUpTo(R.id.nav_flow_graph, true, false)
                                                .build()

                                        }
                                    }
                                }
                            when (flowstep) {
                                FlowStep.MAIN.name -> {
                                    graph = mainNavGraph
                                    navigate(route = TAG_ROUTE_FLOW_MAIN)
                                }
                            }
                        }
                    }
                }
            }
        }
        applicationContext
    }

//    fun createActionFlowNavigationGraph(): NavGraphNavigator {
//        val navHostFragment =
//            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
//        val navController = navHostFragment.navController
//        val navGraphNavigator =
//            navController.navigatorProvider.getNavigator(NavGraphNavigator::class.java)
//
//        val ffgraph = navController.createGraph(
//            startDestination = "flow_splash_init_fragment",
//            route = "nav_flow_graph"
//        ) {
//            // 添加 SplashFlowFragment 目的地
//            fragment<SplashFlowFragment>(route = "flow_splash_init_fragment") {
//                label = TAG_ROUTE_FLOW_SPLASH// 使用字符串资源
//            }
//
//            // 添加 MainFlowFragment 目的地
//            fragment<MainFlowFragment>(route = "flow_main_fragment") {
//                label = "flow_main"
//            }
//        }
//        val graph = navController.navInflater.inflate(R.navigation.action_flow_navigation)
//
//        // 获取 Fragment Destinations
//        val nav_destination_splash =
//            graph.findNode(R.id.flow_splash_init_fragment) as NavDestination
//        val nav_destination_main = graph.findNode(R.id.flow_main_fragment) as NavDestination
//
//
//        // 设置 Fragment Destinations 的属性
//        nav_destination_splash.apply {
//            label = TAG_ROUTE_FLOW_SPLASH// 使用字符串资源
//            //Ref : https://www.cnblogs.com/guanxinjing/p/13878219.html
//            //Ref : https://blog.csdn.net/weixin_52173250/article/details/142391429
//            //Ref : https://blog.csdn.net/u012878270/article/details/130040578
//            //Ref : https://blog.csdn.net/stephen_sun_/article/details/123241134
//            //Ref : https://blog.csdn.net/ayong120/article/details/134402653
//            // 设置 Actions 的属性
//            val navoptions: NavOptions = NavOptions.Builder()
//                .setPopUpTo(R.id.flow_splash_init_fragment, true, false)
////            .setEnterAnim(R.anim.from_right) //进入动画
////            .setExitAnim(R.anim.to_left) //退出动画
////            .setPopEnterAnim(R.anim.to_left) //弹出进入动画
////            .setPopExitAnim(R.anim.from_right) //弹出退出动画
//                .build()
//            // 创建 Actions
//            val action = NavAction(R.id.flow_main_fragment, navoptions)
//            putAction(R.id.action_flow_splash, action)
//        }
//
//        nav_destination_main.apply {
//            label = TAG_ROUTE_FLOW_MAIN
//            val navoptions: NavOptions = NavOptions.Builder()
//                .setPopUpTo(R.id.flow_main_fragment, true, false)
//                .build()
//            val action = NavAction(R.id.flow_main_fragment, navoptions)
//            putAction(R.id.action_flow_main, action)
//        }
//
//
//        // 设置起始 Destination
//        graph.setStartDestination(R.id.flow_splash_init_fragment)
//
//        // 设置 NavController 的 graph
//        navController.graph = graph
//
//        // 导航到起始 Destination
//        navController.navigate(
//            R.id.flow_splash_init_fragment,
//            bundleOf(TAG_FLOW_STEP to FlowStep.SPLASH.name)
//        )
//
//        return navGraphNavigator
//    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onBackPressed() {
        if (System.currentTimeMillis() - exitTime > 2000) {
            exitTime = System.currentTimeMillis()
        } else {
            super.onBackPressed()
        }
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onStop() {
        super.onStop()
    }
}