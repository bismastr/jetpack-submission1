package com.example.jetpack_submission1.domain.repository

import androidx.lifecycle.LiveData
import com.example.jetpack_submission1.data.Resource
import com.example.jetpack_submission1.data.local.entity.MovieDetailEntity
import com.example.jetpack_submission1.data.local.entity.MovieDiscoverEntity
import com.example.jetpack_submission1.data.local.entity.TvDetailEntity
import com.example.jetpack_submission1.domain.model.MovieDetail
import com.example.jetpack_submission1.domain.model.MovieDiscover
import com.example.jetpack_submission1.domain.model.TvDetail
import kotlinx.coroutines.flow.Flow

interface IFilmRepository {

    fun getMovieDiscover(): Flow<Resource<List<MovieDiscover>>>

    fun getTvDiscover(): Flow<Resource<List<MovieDiscover>>>

    fun getTrending(mediaType: String): Flow<Resource<List<MovieDiscover>>>

    fun getTvTrending(mediaType: String): Flow<Resource<List<MovieDiscover>>>

    fun getMovieDetail(movieId: String): Flow<Resource<MovieDetail>>

    fun getTvDetail(tvId: String): Flow<Resource<TvDetail>>

}