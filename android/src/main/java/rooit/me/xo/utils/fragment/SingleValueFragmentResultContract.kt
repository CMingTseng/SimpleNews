package rooit.me.xo.utils.fragment

import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentResultListener
import androidx.lifecycle.LifecycleOwner

const val FragmentResultRequestKey = "requestKey"

enum class Action {
    CLICK ,
    NAV,
    BACK
}

interface FragmentResultContract<T> {
    fun toBundle(result: T): Bundle
    fun fromBundle(bundle: Bundle): T
}

abstract class SingleValueFragmentResultContract<T> : FragmentResultContract<T> {
    override fun toBundle(result: T) = bundleOf(RESULT_KEY to result)

    @Suppress("UNCHECKED_CAST")
    override fun fromBundle(bundle: Bundle): T = bundle.get(RESULT_KEY) as T

    companion object {
        private const val RESULT_KEY = "result"
    }
}

object ResultContract : SingleValueFragmentResultContract<String?>()

inline fun <T> FragmentManager.setFragmentResultListener(contract: FragmentResultContract<T>, lifecycleOwner: LifecycleOwner, crossinline resultListener: (T) -> Unit) {
    setFragmentResultListener( contract::class.java.name,
        lifecycleOwner,
        FragmentResultListener { _, result ->
            resultListener(contract.fromBundle(result))
        })
}