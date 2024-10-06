package rooit.me.xo.ui.flow.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavDestinationBuilder
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.createGraph
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.NavHostFragment.Companion.create
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.navigation.fragment.fragment
import rooit.me.xo.R
import rooit.me.xo.databinding.FragmentFlowSplashBinding
import rooit.me.xo.def.TAG_ROUTE_SPLASH
import rooit.me.xo.ui.flow.BaseFlowFragment
import rooit.me.xo.ui.splash.SplashFragment
import timber.log.Timber

class SplashFlowFragment :
    BaseFlowFragment(R.layout.fragment_flow_splash, R.id.nav_host_splash_fragment) {
    private var _binding: FragmentFlowSplashBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFlowSplashBinding.inflate(inflater, container, false)
        requireActivity()?.apply {
//            window?.apply {
//                statusBarColor = Color.TRANSPARENT
//                decorView?.let {
//                    it.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN)
//                }
//            }
            (this as AppCompatActivity).apply {
                supportActionBar?.apply {
                    hide()
                }
            }
        }

        findNavController(this)?.let { nvc->
            val splashnavGraph = nvc.createGraph(startDestination = TAG_ROUTE_SPLASH) {
                fragment<SplashFragment>(route = TAG_ROUTE_SPLASH) {
                    label = TAG_ROUTE_SPLASH
                    action(R.id.flow_main_fragment) {
                        destinationId = R.id.splash_step1
                        NavOptions.Builder()
                            .setPopUpTo(R.id.nav_flow_graph, true, false)
                            .build()

                    }
                }
            }
            nvc.graph =splashnavGraph
            nvc.navigate(route = TAG_ROUTE_SPLASH)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Timber.e("Show me SplashFlowFragment  flownavcontroller  : $navcontroller")
    }

    override fun processFragmentResults(results: Bundle?) {
        super.processFragmentResults(results)
        results?.let {
            val keys = it.keySet()
//            if (keys.contains(Action.CLICK.action)){
//                navcontroller.navigate(R.id.action_splash_init_to_ad)
//            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}