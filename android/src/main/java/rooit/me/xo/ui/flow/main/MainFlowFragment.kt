package rooit.me.xo.ui.flow.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.navigation.ui.setupWithNavController
import rooit.me.xo.R
import rooit.me.xo.databinding.FragmentFlowMainBinding
import rooit.me.xo.ui.flow.BaseFlowFragment

class MainFlowFragment  : BaseFlowFragment( R.layout.fragment_flow_main, R.id.nav_host_fragment_main ) {
    private var _binding: FragmentFlowMainBinding? = null
    private val binding get() = _binding!!
    private val vm:  MainFlowViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View {
        _binding = FragmentFlowMainBinding.inflate(inflater, container, false)
        arguments?.let { args ->

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