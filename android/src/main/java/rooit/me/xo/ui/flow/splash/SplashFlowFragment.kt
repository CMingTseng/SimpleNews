package rooit.me.xo.ui.flow.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import rooit.me.xo.R
import rooit.me.xo.databinding.FragmentFlowSplashBinding
import rooit.me.xo.ui.flow.BaseFlowFragment

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
        return binding.root
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