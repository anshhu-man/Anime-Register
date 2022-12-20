package com.example.studentregister.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Anime::class], version = 1, exportSchema = false)
abstract class AnimeDatabase : RoomDatabase() {

    abstract fun animeDao():AnimeDao
    companion object {
        @Volatile
        private var INSTANCE : AnimeDatabase? =null
        fun getInstance(context: Context):AnimeDatabase{
            synchronized(this){
                var instance = INSTANCE
                if(instance==null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AnimeDatabase::class.java,
                        "anime_data_database"
                    ).build()
                }
                return instance
            }
        }
    }
}