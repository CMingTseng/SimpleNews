package rooit.me.xo.di

import de.jensklingenberg.ktorfit.Ktorfit
import org.koin.dsl.module

import rooit.me.xo.api.NewsApi

val apiModule = module {
    single(createdAtStart = false) { get<Ktorfit>().create<NewsApi>() }
}