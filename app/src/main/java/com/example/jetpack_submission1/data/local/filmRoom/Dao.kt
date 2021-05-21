package com.example.jetpack_submission1.data.local.filmRoom

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.jetpack_submission1.data.local.entity.MovieDiscoverEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface Dao {
    @Query("SELECT * FROM movie_table")
    fun getAllMovieDiscover(): Flow<List<MovieDiscoverEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieDiscover(film: List<MovieDiscoverEntity>)

    @Query("SELECT * FROM movie_table")
    fun getAllMovieTrending(): Flow<List<MovieDiscoverEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieTrending(trending: List<MovieDiscoverEntity>)
}