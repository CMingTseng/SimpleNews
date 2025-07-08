package rooit.me.xo.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.launch
import rooit.me.xo.databinding.PageLoginBinding
import rooit.me.xo.route.Route.Companion.LOGIN_REQUEST_KEY
import rooit.me.xo.ui.flow.FlowStep
import rooit.me.xo.ui.flow.TAG_FLOW_STEP
import kotlin.getValue

class PageLogin : Fragment() {
    private var _binding: PageLoginBinding? = null
    private val binding get() = _binding!!
    private val vm by viewModels<LoginViewModel>()

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = PageLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                vm.text.collect { newTextValue ->
                    binding.tv.text = newTextValue
                }
            }
        }

        binding.btLogin.setOnClickListener {
            val result = bundleOf(TAG_FLOW_STEP to FlowStep.MAIN.name)
            arguments?.let {
                result.putAll(it)
            }
            setFragmentResult(LOGIN_REQUEST_KEY, result)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}