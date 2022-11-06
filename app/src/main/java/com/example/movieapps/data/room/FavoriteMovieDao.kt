package com.example.movieapps.data.room

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.movieapps.data.model.FavoriteEntity

@Database(entities = [FavoriteEntity::class], version = 1,exportSchema = false)
abstract class FavoriteDatabase : RoomDatabase() {
    abstract fun favoriteMovieDao(): FavoriteMovieDao

    companion object {
        @Volatile
        private var INSTANCE: FavoriteDatabase? = null
        @JvmStatic
        fun getDatabase(context: Context): FavoriteDatabase {
            if (INSTANCE == null) {
                synchronized(FavoriteDatabase::class.java) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        FavoriteDatabase::class.java, "favorite_db")
                        .build()
                }
            }
            return INSTANCE as FavoriteDatabase
        }
    }
}