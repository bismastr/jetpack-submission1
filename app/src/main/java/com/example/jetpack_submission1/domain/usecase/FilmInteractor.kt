package com.example.jetpack_submission1.domain.usecase

import com.example.jetpack_submission1.domain.model.MovieDetail
import com.example.jetpack_submission1.domain.model.MovieDiscover
import com.example.jetpack_submission1.domain.model.TvDetail
import com.example.jetpack_submission1.domain.repository.IFilmRepository
import kotlinx.coroutines.flow.Flow

class FilmInteractor(private val filmRepository: IFilmRepository) : FilmUseCase {
    override fun getMovieDiscover() = filmRepository.getMovieDiscover()

    override fun getTvDiscover() = filmRepository.getTvDiscover()

    override fun getTvTrending(mediaType: String) = filmRepository.getTvTrending(mediaType)

    override fun getTrending(mediaType: String) = filmRepository.getTrending(mediaType)

    override fun getMovieDetail(movieId: String) = filmRepository.getMovieDetail(movieId)

    override fun getTvDetail(tvId: String) = filmRepository.getTvDetail(tvId)

    override fun getMovieFavorite(): Flow<List<MovieDiscover>> = filmRepository.getMovieFavorite()

    override fun getTvFavorite(): Flow<List<MovieDiscover>> = filmRepository.getTvFavorite()

    override fun setMovieFavorite(film: MovieDetail, state: Boolean) =
        filmRepository.setMovieFavorite(film, state)

    override fun setTvFavorite(film: TvDetail, state: Boolean) =
        filmRepository.setTvFavorite(film, state)
}