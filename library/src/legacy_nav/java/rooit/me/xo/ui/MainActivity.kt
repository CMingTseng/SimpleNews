package rooit.me.xo.ui

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.view.Window
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.fragment.NavHostFragment
import rooit.me.xo.R
import rooit.me.xo.databinding.ActivityMainBinding
import rooit.me.xo.ui.flow.FlowStep
import rooit.me.xo.ui.flow.TAG_FLOW_STEP
import rooit.me.xo.utils.fragment.FragmentResultRequestKey

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel : MainViewModel by viewModels()
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
            setKeepOnScreenCondition(){
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
            childFragmentManager.setFragmentResultListener(
                FragmentResultRequestKey,
                this
            ) { requestkey, bundle ->
                if (requestkey == FragmentResultRequestKey) {
                     bundle.getString(TAG_FLOW_STEP)?.let {flowstep->
                         this.navController?.apply {
                             navigateUp()
                             when(flowstep){
                                 FlowStep.MAIN.name->{
                                     graph = this.navInflater.inflate(R.navigation.action_flow_navigation)
                                         .apply {
                                             setStartDestination(R.id.flow_main_fragment)
                                         }
                                     navigate(R.id.flow_main_fragment, bundle)
                                 }
                             }
                         }
                     }
                }
            }
//Ref : https://stackoverflow.com/questions/51173002/how-to-change-start-destination-of-a-navigation-graph-programmatically
            this.navController?.let { nvc ->
                nvc.navInflater.inflate(R.navigation.action_flow_navigation)?.let { navGraph ->
                    navGraph.setStartDestination(R.id.flow_splash_init_fragment)
                    nvc.graph = navGraph
                nvc.navigate(
                    R.id.flow_splash_init_fragment,
                        bundleOf(TAG_FLOW_STEP to FlowStep.SPLASH.name)
                    )
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