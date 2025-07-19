package rooit.me.xo.di

import org.koin.dsl.module
import rooit.me.xo.gateway.ArticleLocalDataSource
import rooit.me.xo.model.db.room.ArticleLocalDataSourceImpl


  val gatewayModule = module {

    single<ArticleLocalDataSource> { ArticleLocalDataSourceImpl(get(), get(), get()) }

}