package rooit.me.xo.di

import androidx.room.Room
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import rooit.me.xo.model.db.room.DB_PATH
import rooit.me.xo.model.db.room.NewsDatabase
import rooit.me.xo.utils.DispatcherProvider

fun getDatabaseModule()= module {
    single {
        Room
            .databaseBuilder<NewsDatabase>(
                context = androidApplication(),
                NewsDatabase::class.java,
                name = DB_PATH
            )
            .fallbackToDestructiveMigration(
                dropAllTables = true
            )
            .setQueryCoroutineContext(get<DispatcherProvider>().io)
            .build()
    }

//    single {
//        val database = get<NewsDatabase>() // Koin 會自動注入 NewsDatabase 實例
//        database.getArticleDao() // 或 database.articleDao()，根據您的 NewsDatabase 中的方法名
//    }
    factory {
        val database = get<NewsDatabase>() // Koin 仍然會注入 NewsDatabase 的單例
        database.getArticleDao() // 或者 database.articleDao()
    }
//    factory { get<NewsDatabase>().getArticleDao() }
}