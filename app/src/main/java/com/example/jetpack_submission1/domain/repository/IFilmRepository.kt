package com.example.jetpack_submission1.domain.repository

import androidx.lifecycle.LiveData
import com.example.jetpack_submission1.data.Resource
import com.example.jetpack_submission1.data.local.entity.MovieDetailEntity
import com.example.jetpack_submission1.data.local.entity.MovieDiscoverEntity
import com.example.jetpack_submission1.data.local.entity.TvDetailEntity
import com.example.jetpack_submission1.domain.model.MovieDiscover
import kotlinx.coroutines.flow.Flow

interface IFilmRepository {

    fun getMovieDiscover(): Flow<Resource<List<MovieDiscover>>>

    fun getTvDiscover(): LiveData<List<MovieDiscoverEntity>>

    fun getTrending(mediaType: String): Flow<Resource<List<MovieDiscover>>>

    fun getMovieDetail(movieId: String): LiveData<MovieDetailEntity>

    fun getTvDetail(tvId: String): LiveData<TvDetailEntity>

}