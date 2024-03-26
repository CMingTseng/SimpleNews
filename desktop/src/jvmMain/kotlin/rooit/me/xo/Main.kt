package rooit.me.xo

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import org.koin.core.context.startKoin
import rooit.me.xo.di.NetworkModule
import rooit.me.xo.di.apiModule
import rooit.me.xo.di.dbModule
import rooit.me.xo.di.repositoryModule
import rooit.me.xo.di.viewModelModule
import rooit.me.xo.ui.UIShow

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        startKoin {
            modules(
                listOf(
                    apiModule,
                    dbModule,
                    NetworkModule.KtorfitModule,
                    repositoryModule,
                    viewModelModule
                )
            )
        }
        UIShow()
    }
}