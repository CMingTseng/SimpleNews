package rooit.me.xo.di

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import org.koin.core.qualifier.named
import org.koin.dsl.module
import rooit.me.xo.app.BuildConfig
import rooit.me.xo.utils.CoroutineScopeProvider
import rooit.me.xo.utils.DefaultDispatcherProvider
import rooit.me.xo.utils.DispatcherProvider

val AppInfoModule = module {
    single(named("API_URL")) { BuildConfig.BASE_URL }
    single(named("API_KEY")) { BuildConfig.API_KEY }
    single<CoroutineScopeProvider> {
        object : CoroutineScopeProvider {
            override val ui = MainScope()
            override val default = CoroutineScope(Dispatchers.Default)
            override val io = CoroutineScope(Dispatchers.IO)
            override val unconfined = CoroutineScope(Dispatchers.Unconfined)
        }
    }

//    single<DispatcherProvider> {
//        object : DispatcherProvider {
//            override val ui = Dispatchers.Main
//            override val default = Dispatchers.Default
//            override val io = Dispatchers.IO
//            override val unconfined = Dispatchers.Unconfined
//        }
//    }
    single<DispatcherProvider> {
        DefaultDispatcherProvider()
    }
}