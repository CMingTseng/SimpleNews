package rooit.me.xo.utils

import kotlinx.coroutines.CoroutineDispatcher

interface DispatcherProvider {
    // val main: CoroutineDispatcher
    val ui: CoroutineDispatcher
    val default: CoroutineDispatcher
    val io: CoroutineDispatcher
    val unconfined: CoroutineDispatcher
}