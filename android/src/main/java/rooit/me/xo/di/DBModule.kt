package rooit.me.xo.di

import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import org.koin.dsl.module
import rooit.me.xo.model.db.realm.ArticleRealm
import rooit.me.xo.model.db.realm.SourceRealm

val dbModule = module {
    single {
        val config = RealmConfiguration.create(
            schema = setOf(ArticleRealm::class, SourceRealm::class)
        )
        Realm.open(config)
    }
}