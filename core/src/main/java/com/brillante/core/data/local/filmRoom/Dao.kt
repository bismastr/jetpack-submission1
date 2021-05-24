package com.brillante.core.data.local.filmRoom

import androidx.room.*
import androidx.room.Dao
import com.brillante.core.data.local.entity.MovieDetailEntity
import com.brillante.core.data.local.entity.MovieDiscoverEntity
import com.brillante.core.data.local.entity.TvDetailEntity
import com.brillante.core.data.local.entity.TvDiscoverEntity
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

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMovieTrending(trending: List<MovieDiscoverEntity>)

    //Tv
    @Query("SELECT * FROM tv_table")
    fun getAllTvDiscover(): Flow<List<TvDiscoverEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTvDiscover(film: List<TvDiscoverEntity>)

    @Query("SELECT * FROM tv_table where trending = 1")
    fun getAllTvTrending(): Flow<List<TvDiscoverEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertTvTrending(trending: List<TvDiscoverEntity>)

    //movieDetail
    @Query("SELECT * FROM movie_detail_table where id=:id")
    fun getMovieDetail(id: String): Flow<MovieDetailEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMovieDetail(film: MovieDetailEntity)

    //tvDetail
    @Query("SELECT * FROM tv_detail_table where id=:id")
    fun getTvDetail(id: String): Flow<TvDetailEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertTvDetail(film: TvDetailEntity)

    //favorite
    @Update
    fun updateMovieFavorite(favorite: MovieDetailEntity)

    @Update
    fun updateTvFavorite(favorite: TvDetailEntity)

    @Query("SELECT * FROM movie_detail_table where isFavorite = 1")
    fun getMovieFavorite(): Flow<List<MovieDetailEntity>>

    @Query("SELECT * FROM tv_detail_table where isFavorite = 1")
    fun getTvFavorite(): Flow<List<TvDetailEntity>>

}