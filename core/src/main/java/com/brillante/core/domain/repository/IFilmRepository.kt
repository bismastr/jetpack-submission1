package com.brillante.core.domain.repository


import com.brillante.core.data.Resource
import com.brillante.core.domain.model.MovieDetail
import com.brillante.core.domain.model.MovieDiscover
import com.brillante.core.domain.model.TvDetail
import kotlinx.coroutines.flow.Flow

interface IFilmRepository {

    fun getMovieDiscover(): Flow<Resource<List<MovieDiscover>>>

    fun getTvDiscover(): Flow<Resource<List<MovieDiscover>>>

    fun getTrending(mediaType: String): Flow<Resource<List<MovieDiscover>>>

    fun getTvTrending(mediaType: String): Flow<Resource<List<MovieDiscover>>>

    fun getMovieDetail(movieId: String): Flow<Resource<MovieDetail>>

    fun getTvDetail(tvId: String): Flow<Resource<TvDetail>>

    fun getMovieFavorite(): Flow<List<MovieDiscover>>

    fun getTvFavorite(): Flow<List<MovieDiscover>>

    fun setMovieFavorite(film: MovieDetail, state: Boolean)

    fun setTvFavorite(film: TvDetail, state: Boolean)

}