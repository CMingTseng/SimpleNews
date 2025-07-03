package rooit.me.xo.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import rooit.me.xo.databinding.FragmentSplashBinding
import rooit.me.xo.databinding.PageLoginBinding
import rooit.me.xo.route.Route.Companion.LOGIN_REQUEST_KEY
import rooit.me.xo.route.Route.Companion.SPLASH_REQUEST_KEY
import rooit.me.xo.ui.flow.FlowStep
import rooit.me.xo.ui.flow.TAG_FLOW_STEP
import rooit.me.xo.ui.splash.SplashStep
import rooit.me.xo.ui.splash.SplashViewModel
import rooit.me.xo.ui.splash.TAG_SPLASH_STEP
import timber.log.Timber
import kotlin.getValue

class PageLogin : Fragment() {
    private var _binding: PageLoginBinding? = null
    private val binding get() = _binding!!
    private val vm by viewModels<LoginViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = PageLoginBinding.inflate(inflater, container, false)
        vm.text.observe(viewLifecycleOwner) {
            binding.tv.text = it
        }
        binding.btLogin.setOnClickListener {
            Timber.e("Show me Normal")
            val result = bundleOf(TAG_FLOW_STEP to FlowStep.MAIN.name)
            arguments?.let {
                result.putAll(it)
            }
            setFragmentResult(LOGIN_REQUEST_KEY, result)
        }
        return binding.root
    }
}