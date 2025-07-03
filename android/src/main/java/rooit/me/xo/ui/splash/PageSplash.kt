package rooit.me.xo.ui.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.lifecycle.ViewModelProvider
import rooit.me.xo.databinding.FragmentSplashBinding
import rooit.me.xo.route.Route.Companion.SPLASH_REQUEST_KEY
import rooit.me.xo.ui.flow.FlowStep
import rooit.me.xo.ui.flow.TAG_FLOW_STEP
import rooit.me.xo.utils.fragment.FragmentResultRequestKey
import timber.log.Timber

class PageSplash : Fragment() {
    private var _binding: FragmentSplashBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        arguments?.let {
            it.putSerializable(TAG_SPLASH_STEP, SplashStep.STEP1.name)
        }
        val vm =
            ViewModelProvider(this).get(SplashViewModel::class.java)

        _binding = FragmentSplashBinding.inflate(inflater, container, false)

        vm.text.observe(viewLifecycleOwner) {
            binding.textHome.text = it
        }

        binding.btLogin.setOnClickListener {
            val result = bundleOf(
//                TAG_SPLASH_STEP to SplashStep.SPLASH_FINISH.name,
                TAG_FLOW_STEP to FlowStep.LOGIN_SIGNUP.name
            )
            arguments?.let {
                result.putAll(it)
            }
            setFragmentResult(SPLASH_REQUEST_KEY, result)
        }
        binding.btNormal.setOnClickListener {
            val result = bundleOf(
//                TAG_SPLASH_STEP to SplashStep.SPLASH_FINISH.name,
                TAG_FLOW_STEP to FlowStep.MAIN.name
            )
            arguments?.let {
                result.putAll(it)
            }
            setFragmentResult(SPLASH_REQUEST_KEY, result)
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}