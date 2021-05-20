package com.example.jetpack_submission1.domain.usecase

import androidx.lifecycle.LiveData
import com.example.jetpack_submission1.data.local.entity.MovieDetailEntity
import com.example.jetpack_submission1.data.local.entity.MovieDiscoverEntity
import com.example.jetpack_submission1.data.local.entity.TvDetailEntity

interface FilmUseCase {
    fun getMovieDiscover(): LiveData<List<MovieDiscoverEntity>>

    fun getTvDiscover(): LiveData<List<MovieDiscoverEntity>>

    fun getTrending(mediaType: String): LiveData<List<MovieDiscoverEntity>>

    fun getMovieDetail(movieId: String): LiveData<MovieDetailEntity>

    fun getTvDetail(tvId: String): LiveData<TvDetailEntity>
}