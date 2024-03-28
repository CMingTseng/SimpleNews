package rooit.me.xo.di

import org.koin.core.module.Module
import org.koin.dsl.module
import rooit.me.xo.repository.AuthRepository
import rooit.me.xo.repository.NewsRepository

public val repositoryModule: Module = module {
    single { NewsRepository(get(),get()) }
//    singleOf(::NewsRepository(::NewsApi()))
    single { AuthRepository(get() ) }
}