package rooit.me.xo.di

import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module
import rooit.me.xo.ui.news.NewsViewModel

public val viewModelModule: Module = module {
    factory {
        NewsViewModel(
            repo = get(),
            apikey = get(named("API_KEY"))
        )
    }
}