package com.example.jetpack_submission1.data.local.filmRoom

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.jetpack_submission1.data.local.entity.MovieDetailEntity
import com.example.jetpack_submission1.data.local.entity.MovieDiscoverEntity
import com.example.jetpack_submission1.data.local.entity.TvDetailEntity
import com.example.jetpack_submission1.data.local.entity.TvDiscoverEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface Dao {
    //Movie
    @Query("SELECT * FROM movie_table")
    fun getAllMovieDiscover(): Flow<List<MovieDiscoverEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieDiscover(film: List<MovieDiscoverEntity>)

    @Query("SELECT * FROM movie_table where trending = 1")
    fun getAllMovieTrending(): Flow<List<MovieDiscoverEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieTrending(trending: List<MovieDiscoverEntity>)

    //Tv
    @Query("SELECT * FROM tv_table")
    fun getAllTvDiscover(): Flow<List<TvDiscoverEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTvDiscover(film: List<TvDiscoverEntity>)

    @Query("SELECT * FROM tv_table where trending = 1")
    fun getAllTvTrending(): Flow<List<TvDiscoverEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTvTrending(trending: List<TvDiscoverEntity>)

    //movieDetail
    @Query("SELECT * FROM movie_detail_table where id=:id")
    fun getMovieDetail(id: String): Flow<MovieDetailEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieDetail(film: MovieDetailEntity)

    //tvDetail
    @Query("SELECT * FROM tv_detail_table where id=:id")
    fun getTvDetail(id: String): Flow<TvDetailEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTvDetail(film: TvDetailEntity)
}