package rooit.me.xo

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.view.Window
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.navigation.fragment.NavHostFragment
import rooit.me.xo.databinding.ActivityMainBinding
import rooit.me.xo.ui.flow.FlowStep
import rooit.me.xo.ui.flow.TAG_FLOW_STEP
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.findNavController
import rooit.me.xo.navigation.NavigationProvider
import rooit.me.xo.route.Route
import rooit.me.xo.route.Route.Companion.LOGIN_REQUEST_KEY
import rooit.me.xo.route.Route.Companion.SPLASH_REQUEST_KEY
import rooit.me.xo.ui.splash.TAG_SPLASH_STEP

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
            this.navController.let { nvc ->
                if (savedInstanceState == null) {
                    nvc.graph = NavigationProvider.getBuilder().build(nvc, Route.Splash)
                    val args = bundleOf(TAG_FLOW_STEP to FlowStep.SPLASH.name)
                    intent?.extras?.let { bundle ->
                        args.putAll(bundle)
                    }
                }
            }

            childFragmentManager.setFragmentResultListener(SPLASH_REQUEST_KEY,this) { requestkey, bundle ->
                bundle.getString(TAG_FLOW_STEP)?.let { flowstep ->
                    switchToNormalGraph(bundle)
                }
            }

            childFragmentManager.setFragmentResultListener(LOGIN_REQUEST_KEY,this) { requestkey, bundle ->
                bundle.getString(TAG_FLOW_STEP)?.let { flowstep ->
                    switchToNormalGraph(bundle)
                }
            }
        }
        applicationContext
    }

    private fun switchToNormalGraph(bundle: Bundle? = null) {
        bundle?.let { args ->
            findNavController(R.id.nav_host_fragment)?.let { navController ->
                if (args.getString(TAG_SPLASH_STEP).isNullOrEmpty()) {
                    args.getString(TAG_FLOW_STEP)?.let { flowstep ->
                        when (flowstep) {
                            FlowStep.MAIN.name -> {
                                navController.navigate(R.id.action_Login_to_News)
                            }
                        }
                    }
                } else {
                    args.getString(TAG_FLOW_STEP)?.let { flowstep ->
                        when (flowstep) {
                            FlowStep.MAIN.name -> {
                                navController.navigate(R.id.action_Splash_to_Main)
                            }

                            FlowStep.LOGIN_SIGNUP.name -> {
                                navController.navigate(R.id.action_Splash_to_Login)
                            }
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