package rooit.me.xo.di

import org.koin.dsl.module
import retrofit2.Retrofit
import rooit.me.xo.api.NewsApi

val apiModule = module {
    single(createdAtStart = false) { get<Retrofit>().create(NewsApi::class.java) }
}