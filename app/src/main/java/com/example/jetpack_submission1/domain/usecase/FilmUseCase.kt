package com.example.jetpack_submission1.domain.usecase

import com.example.jetpack_submission1.data.Resource
import com.example.jetpack_submission1.domain.model.MovieDetail
import com.example.jetpack_submission1.domain.model.MovieDiscover
import com.example.jetpack_submission1.domain.model.TvDetail
import kotlinx.coroutines.flow.Flow

interface FilmUseCase {
    fun getMovieDiscover(): Flow<Resource<List<MovieDiscover>>>

    fun getTvDiscover(): Flow<Resource<List<MovieDiscover>>>

    fun getTvTrending(mediaType: String): Flow<Resource<List<MovieDiscover>>>

    fun getTrending(mediaType: String): Flow<Resource<List<MovieDiscover>>>

    fun getMovieDetail(movieId: String): Flow<Resource<MovieDetail>>

    fun getTvDetail(tvId: String): Flow<Resource<TvDetail>>

    fun getMovieFavorite(): Flow<List<MovieDiscover>>

    fun getTvFavorite(): Flow<List<MovieDiscover>>

    fun setMovieFavorite(film: MovieDetail, state: Boolean)

    fun setTvFavorite(film: TvDetail, state: Boolean)
}