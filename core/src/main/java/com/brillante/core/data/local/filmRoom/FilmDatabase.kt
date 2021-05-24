package com.brillante.core.data.local.filmRoom

import androidx.room.Database
import androidx.room.RoomDatabase
import com.brillante.core.data.local.entity.*

@Database(
    entities = [MovieDiscoverEntity::class, TvDiscoverEntity::class, MovieDetailEntity::class, TvDetailEntity::class, FavoriteEntity::class],
    version = 1,
    exportSchema = false
)
abstract class FilmDatabase : RoomDatabase() {
    abstract fun Dao(): Dao

}