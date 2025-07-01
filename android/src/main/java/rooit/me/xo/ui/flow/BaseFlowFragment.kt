package rooit.me.xo.ui.flow

import android.os.Bundle
import android.view.View
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import rooit.me.xo.utils.fragment.FragmentResultRequestKey

abstract class BaseFlowFragment(
    @LayoutRes layoutId: Int,
    @IdRes private val navHostFragmentId: Int
) : Fragment(layoutId) {
    protected lateinit var navHostFragment: NavHostFragment
    protected lateinit var navcontroller: NavController
    private var parentArgs: Bundle? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let { args ->
            parentArgs = args
        }
        (childFragmentManager.findFragmentById(navHostFragmentId) as? NavHostFragment)?.apply {
            parentArgs?.let { args ->
                childFragmentManager.findFragmentById(navHostFragmentId)?.let {
                    it.arguments = args
                }
            }
            navHostFragment = this
            navcontroller = navController
            childFragmentManager.setFragmentResultListener(
                FragmentResultRequestKey,
                this
            ) { requestkey, results ->
//                Timber.e("Flow FragmentResult childFragmentManager Result  : $results")
                if (requestkey == FragmentResultRequestKey) {
                    processFragmentResults(results)
                }
            }
        }
        initUI()
    }

    protected open fun initUI() {
    }

    protected open fun processFragmentResults(results: Bundle?) {
        results?.let {
            setFragmentResult(FragmentResultRequestKey, it)
        }
    }
}