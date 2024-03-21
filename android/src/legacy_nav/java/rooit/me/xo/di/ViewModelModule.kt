package rooit.me.xo.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import rooit.me.xo.ui.news.NewsViewModel

val viewModelModule = module {
    viewModel { NewsViewModel(get()) }
}