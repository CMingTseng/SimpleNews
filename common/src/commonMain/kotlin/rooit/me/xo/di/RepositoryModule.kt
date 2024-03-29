package rooit.me.xo.di

import org.koin.core.module.Module
import org.koin.dsl.module
import rooit.me.xo.repository.live.AuthRepository
import rooit.me.xo.repository.NewsRepository
import rooit.me.xo.repository.live.UserRepository

public val repositoryModule: Module = module {
    single { NewsRepository(get(),get()) }
//    singleOf(::NewsRepository(::NewsApi()))
    single { AuthRepository(get() ) }

    single { UserRepository(get() ) }
}