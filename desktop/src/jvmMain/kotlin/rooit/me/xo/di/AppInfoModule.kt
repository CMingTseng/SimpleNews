package rooit.me.xo.di

import org.koin.core.module.Module
import org.koin.dsl.module
import rooit.me.xo.app.BuildConfig

public val appInfoModule: Module = module {
    single() {
        BuildConfig.BASE_URL
    } 
}