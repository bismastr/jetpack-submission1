package com.example.jetpack_submission1.data.local

import com.example.jetpack_submission1.data.local.entity.MovieDiscoverEntity
import com.example.jetpack_submission1.data.local.entity.TvDiscoverEntity
import com.example.jetpack_submission1.data.local.filmRoom.Dao
import kotlinx.coroutines.flow.Flow

class LocalDataSource private constructor(private val filmDao: Dao){
    companion object {
        private var instance: LocalDataSource? = null

        fun getInstance(filmDao: Dao): LocalDataSource =
            instance ?: synchronized(this){
                instance ?: LocalDataSource(filmDao)
            }
    }

    fun getAllMovieDiscover(): Flow<List<MovieDiscoverEntity>> = filmDao.getAllMovieDiscover()

    suspend fun insertMovieDiscover(filmList: List<MovieDiscoverEntity>) = filmDao.insertMovieDiscover(filmList)

    fun getAllMovieTrending(): Flow<List<MovieDiscoverEntity>> = filmDao.getAllMovieTrending()

    suspend fun insertMovieTrending(filmList: List<MovieDiscoverEntity>) = filmDao.insertMovieTrending(filmList)

    //tv
    fun getAllTvDiscover(): Flow<List<TvDiscoverEntity>> = filmDao.getAllTvDiscover()

    suspend fun insertTvDiscover(tvList: List<TvDiscoverEntity>) = filmDao.insertTvDiscover(tvList)

    fun getAllTvTrending(): Flow<List<TvDiscoverEntity>> = filmDao.getAllTvTrending()

    suspend fun insertTvTrending(tvList: List<TvDiscoverEntity>) = filmDao.insertTvTrending(tvList)
}
