package com.example.jetpack_submission1.data.local.filmRoom

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.jetpack_submission1.data.local.entity.MovieDiscoverEntity

@Database(entities = [MovieDiscoverEntity::class], version = 1)
abstract class FilmDatabase: RoomDatabase() {
    abstract fun Dao(): Dao

    companion object {
        @Volatile
        private var INSTANCE: FilmDatabase? = null

        @JvmStatic
        fun getDatabase(context: Context): FilmDatabase {
            if (INSTANCE == null) {
                synchronized(FilmDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        FilmDatabase::class.java,
                        "movie_database"
                    ).build()
                }
            }
            return INSTANCE as FilmDatabase
        }
    }
}