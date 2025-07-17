package rooit.me.xo.utils

import kotlinx.coroutines.CoroutineScope

interface CoroutineScopeProvider {
    val ui: CoroutineScope
    val default: CoroutineScope
    val io: CoroutineScope
    val unconfined: CoroutineScope
}