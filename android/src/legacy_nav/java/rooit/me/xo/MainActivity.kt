package rooit.me.xo

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.view.Window
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.navigation.fragment.NavHostFragment
import rooit.me.xo.databinding.ActivityMainBinding
import rooit.me.xo.ui.flow.FlowStep
import rooit.me.xo.ui.flow.TAG_FLOW_STEP
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import androidx.navigation.navOptions
import rooit.me.xo.navigation.NavigationProvider
import rooit.me.xo.route.Route
import rooit.me.xo.route.Route.Companion.LOGIN_REQUEST_KEY
import rooit.me.xo.route.Route.Companion.SPLASH_REQUEST_KEY
import rooit.me.xo.ui.splash.TAG_SPLASH_STEP
import rooit.me.xo.utils.navigate
import rooit.me.xo.utils.toJsonString
import rooit.me.xo.utils.toQueryString

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
        (supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as? NavHostFragment)?.apply {
            navController.let { nvc -> // 直接使用 this.navController 更簡潔
                if (savedInstanceState == null) {
//                    nvc.graph= NavigationProvider.getBuilder().build(nvc, Route.Splash,R.navigation.navi_splash)
                    nvc.graph= NavigationProvider.getBuilder().build(nvc, Route.Splash)
                    val args = bundleOf(TAG_FLOW_STEP to FlowStep.SPLASH.name)
                    intent?.extras?.let { bundle ->
                        args.putAll(bundle)
                    }
                }
            }

            childFragmentManager.setFragmentResultListener(SPLASH_REQUEST_KEY,this) { requestkey, bundle ->
                bundle.remove(TAG_SPLASH_STEP)
                val options = navOptions {
                    popUpTo(Route.Splash.routeName) {
                        inclusive = true
                    }
                }
                switchDestination(bundle, options)
            }

            childFragmentManager.setFragmentResultListener(LOGIN_REQUEST_KEY,this) { requestkey, bundle ->
                val options = navOptions {
                    popUpTo("${Route.Login.routeName}") {//TODO use args need "/"
                        inclusive = true
                    }
                }
                switchDestination(bundle, options)
            }
        }
    }

    private fun switchDestination(bundle: Bundle? = null, option: NavOptions?) {
        bundle?.let { args ->
            val queryString = args.toQueryString()
            val jsonString = args.toJsonString()
            findNavController(R.id.nav_host_fragment)?.let { navController ->
                args.getString(TAG_FLOW_STEP)?.let { flowstep ->
                    args.remove(TAG_FLOW_STEP)
                    when (flowstep) {
                        FlowStep.MAIN.name -> {
                            navController.popBackStack()
                            navController.navigate("${Route.News.routeName}/${jsonString}", option)
                        }

                        FlowStep.LOGIN_SIGNUP.name -> {
                            navController.navigate(Route.Login.routeName, args, option)
                        }
                    }
                }
            }
        }
    }

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