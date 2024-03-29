package rooit.me.xo.di

import de.jensklingenberg.ktorfit.Ktorfit
import org.koin.core.module.Module
import org.koin.dsl.module
import rooit.me.xo.api.live.AuthApis
import rooit.me.xo.api.NewsApi
import rooit.me.xo.api.live.UserApis

public val apiModule: Module = module {
    single(createdAtStart = false) { get<Ktorfit>().create<NewsApi>() }
    single(createdAtStart = false) { get<Ktorfit>().create<AuthApis>() }
    single() { get<Ktorfit>().create<UserApis>() }
}