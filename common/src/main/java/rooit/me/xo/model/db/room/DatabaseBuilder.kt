package rooit.me.xo.model.db.room

import android.content.Context
import androidx.room.Room

class DatabaseBuilder(private val context: Context) {
      fun build(): NewsDatabase {
        val dbFile = context.getDatabasePath("news.db")
        return Room.databaseBuilder<NewsDatabase>(
            context = context.applicationContext,
            name = dbFile.absolutePath
        ).build()
    }
}