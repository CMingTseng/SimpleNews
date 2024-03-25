package rooit.me.xo.di

import org.koin.dsl.module
import rooit.me.xo.repository.NewsRepository

val repositoryModule = module {
    single { NewsRepository(get(),get()) }
//    singleOf(::NewsRepository(::NewsApi()))
}