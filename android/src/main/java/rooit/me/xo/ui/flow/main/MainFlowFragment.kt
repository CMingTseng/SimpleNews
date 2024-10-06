package rooit.me.xo.ui.flow.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.NavOptions
import androidx.navigation.createGraph
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.navigation.fragment.fragment
import rooit.me.xo.R
import rooit.me.xo.databinding.FragmentFlowMainBinding
import rooit.me.xo.def.TAG_ROUTE_NEW
import rooit.me.xo.ui.flow.BaseFlowFragment
import rooit.me.xo.ui.news.NewsFragment

class MainFlowFragment  : BaseFlowFragment( R.layout.fragment_flow_main, R.id.nav_host_fragment_main ) {
    private var _binding: FragmentFlowMainBinding? = null
    private val binding get() = _binding!!
    private val vm:  MainFlowViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View {
        _binding = FragmentFlowMainBinding.inflate(inflater, container, false)
        arguments?.let { args ->

        }
        findNavController(this)?.let { nvc->
            val splashnavGraph = nvc.createGraph(startDestination = TAG_ROUTE_NEW) {
                fragment<NewsFragment>(route = TAG_ROUTE_NEW) {
                    label = TAG_ROUTE_NEW
                    action(R.id.mobile_navigation) {
                        destinationId = R.id.navigation_news
                        NavOptions.Builder()
                            .setPopUpTo(R.id.mobile_navigation, true, false)
                            .build()

                    }
                }
            }
            nvc.graph =splashnavGraph
            nvc.navigate(route =TAG_ROUTE_NEW)
        }
        return binding.root
    }

    override fun initUI() {
//        binding.bottomnavigation.setupWithNavController(navcontroller)
//        requireActivity()?.let {
//            (it as AppCompatActivity)?.apply {
//
//            }
//        }
    }

    override fun onResume() {
        super.onResume()
        vm.fetchSelfInfo()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}