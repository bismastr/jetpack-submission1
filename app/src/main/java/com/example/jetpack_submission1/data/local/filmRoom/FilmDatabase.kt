package com.example.jetpack_submission1.data.local.filmRoom

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.jetpack_submission1.data.local.entity.*

@Database(
    entities = [MovieDiscoverEntity::class, TvDiscoverEntity::class, MovieDetailEntity::class, TvDetailEntity::class, FavoriteEntity::class],
    version = 1,
    exportSchema = false
)
abstract class FilmDatabase : RoomDatabase() {
    abstract fun Dao(): Dao

}