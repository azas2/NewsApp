package com.mala.newsapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.mala.newsapp.model.Article
import java.util.concurrent.locks.Lock
@Database(entities = [Article::class], version = 3)
@TypeConverters(Converters::class)
abstract class DataBase:RoomDatabase() {
    abstract fun getAllArticleDao():Dao
    companion object{
        private var instance:DataBase?=null
        private val Lock=Any()

        operator fun invoke(context:Context)= instance?: synchronized(Lock){
            instance?: createDatabase(context).also{ instance=it}
        }

        private fun createDatabase(context: Context)=
            Room.databaseBuilder(
                context.applicationContext,
                DataBase::class.java,
                "article_db.db"
            )
                .fallbackToDestructiveMigration()
                .build()


    }
}