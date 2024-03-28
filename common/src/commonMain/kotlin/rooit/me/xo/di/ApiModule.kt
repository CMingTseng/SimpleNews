package rooit.me.xo.di

import de.jensklingenberg.ktorfit.Ktorfit
import org.koin.core.module.Module
import org.koin.dsl.module
import rooit.me.xo.api.AuthApis
import rooit.me.xo.api.NewsApi

public val apiModule: Module = module {
    single(createdAtStart = false) { get<Ktorfit>().create<NewsApi>() }
    single(createdAtStart = false) { get<Ktorfit>().create<AuthApis>() }

}