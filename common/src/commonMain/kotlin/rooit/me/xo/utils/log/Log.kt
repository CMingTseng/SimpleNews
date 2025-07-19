package rooit.me.xo.utils.log

import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier

//FIXME https://github.com/AAkira/Napier
public object Log {
    private var TAG: String = "Please init your TAG"

    public fun init(tag: String? = null) {
        TAG = tag ?: TAG
        Napier.base(DebugAntilog())
    }

    private fun resolveTag(tempTag: String?) = tempTag ?: TAG

    public fun w(tag: String? = TAG,msg: String  ) {
        Napier.w(msg, throwable = null, tag = resolveTag(tag))
    }

    public fun i( tag: String? = TAG,msg: String,) {
        Napier.i(msg, throwable = null, tag = resolveTag(tag))
    }

    public fun d(tag: String? = TAG,msg: String, ) {
        Napier.d(msg, throwable = null, tag = resolveTag(tag))
    }

    public fun e( tag: String? =TAG,msg: String, throwable: Throwable? = null) {
        Napier.e(msg, throwable = throwable, tag = resolveTag(tag))
    }
}