package rooit.me.xo.model.db.room

import androidx.room.Database
import androidx.room.RoomDatabase
import rooit.me.xo.dao.ArticleDao

const val DATABASE_NAME = "news.db"
private val APP_DATA_DIR = "."
//const val DB_PATH = "$APP_DATA_DIR/database/$DATABASE_NAME"
const val DB_PATH = "${DATABASE_NAME}"
@Database(entities = [ArticleEntity::class], version = 1,
    exportSchema = false)
abstract class NewsDatabase : RoomDatabase(){
    abstract fun getArticleDao(): ArticleDao
}