package rooit.me.xo

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import rooit.me.xo.app.BuildConfig
import rooit.me.xo.di.AppInfoModule
import rooit.me.xo.di.NetworkModule
import rooit.me.xo.di.apiModule
import rooit.me.xo.di.gatewayModule
import rooit.me.xo.di.getDatabaseModule
import rooit.me.xo.di.repositoryModule
import rooit.me.xo.di.viewModelModule
import rooit.me.xo.utils.log.Log

class MainApp : Application() {
    companion object {
        lateinit var instance: MainApp
            private set
    }
    override fun onCreate() {
        super.onCreate()
        Log.init(packageName)
        startKoin {
            // Log Koin into Android logger
            androidLogger(Level.DEBUG)
            // Reference Android context
            androidContext(this@MainApp)
            // Load modules
//            modules(NetworkModule)
            modules(listOf(
                AppInfoModule,
                NetworkModule,
                apiModule,
                repositoryModule,
                viewModelModule,
                gatewayModule,
                getDatabaseModule()
            ))
        }

        instance = this
    }
}