package rooit.me.xo.utils.log

import io.github.aakira.napier.Napier

public object LogUtils {

    public fun info(tag: String, message: String) {
        Napier.i(message, null, tag)
    }

    public fun debug(tag: String, message: String) {
        Napier.d(message, null, tag)
    }

    public fun error(tag: String, message: String, throwable: Throwable?) {
        Napier.e(message, throwable, tag)
    }
}