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
import rooit.me.xo.utils.fragment.FragmentResultRequestKey
import timber.log.Timber
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.findNavController
import rooit.me.xo.navigation.NavigationProvider
import rooit.me.xo.route.Route

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
            //Ref : https://stackoverflow.com/questions/51173002/how-to-change-start-destination-of-a-navigation-graph-programmatically
            this.navController.let { nvc ->
                val navGraph = NavigationProvider.getBuilder().build(
                    nvc,
                    Route.Splash,
                    R.navigation.navi_splash,
                )
                nvc.graph = navGraph
                val args = bundleOf(TAG_FLOW_STEP to FlowStep.SPLASH.name)
                intent?.extras?.let { bundle ->
                    args.putAll(bundle)
                }
                nvc.navigate(
                    Route.Splash.id,
                    args
                )
            }

            childFragmentManager.setFragmentResultListener(
                FragmentResultRequestKey,
                this
            ) { requestkey, bundle ->
                if (requestkey == FragmentResultRequestKey) {
                    Timber.e("Show FragmentResult bundle $bundle")
                    bundle.getString(TAG_FLOW_STEP)?.let { flowstep ->
                        this.navController?.apply {
                            navigateUp()
                            when (flowstep) {
                                FlowStep.MAIN.name -> {
                                    graph = NavigationProvider.getBuilder().build(
                                        this,
                                        Route.News,
                                        R.navigation.navi_news,
                                    )
                                    navigate(Route.News.id, bundle)
                                }
                            }
                        }
                    }
                }
            }
        }
        applicationContext
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