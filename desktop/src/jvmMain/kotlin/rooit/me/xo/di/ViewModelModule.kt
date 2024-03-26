package rooit.me.xo.di

import org.koin.core.module.Module
import org.koin.dsl.module
import rooit.me.xo.ui.news.NewsViewModel

public val viewModelModule: Module = module {
//    viewModel { NewsViewModel(get()) }
    factory {
        NewsViewModel(
            repo = get(),
        )
    }
}