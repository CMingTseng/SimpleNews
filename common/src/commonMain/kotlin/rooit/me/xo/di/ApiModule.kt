package rooit.me.xo.di

import de.jensklingenberg.ktorfit.Ktorfit
import org.koin.dsl.module
import rooit.me.xo.api.NewsApi

val apiModule = module {
//    single(createdAtStart = false) { get<Ktorfit>().create<NewsApi>() }
    single(createdAtStart = false) {
        val ktorfitInstance = get<Ktorfit>()
        println("Show Koin get<Ktorfit>() returned class : ${ktorfitInstance::class.simpleName}")
        if (ktorfitInstance !is Ktorfit) {
            throw IllegalStateException("Expected Ktorfit instance but got ${ktorfitInstance::class.simpleName}")
        }
        ktorfitInstance.create<NewsApi>()
//        ktorfitInstance.createNewsApi()// build/generated/ksp/<buildVariant>/kotlin/
    }
}