package rooit.me.xo.di

import org.koin.core.qualifier.named
import org.koin.dsl.module
import rooit.me.xo.app.BuildConfig

val AppInfoModule = module {
    single(named("API_URL")) { BuildConfig.BASE_URL }
    single(named("API_KEY")) { BuildConfig.API_KEY }
}