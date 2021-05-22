package com.example.jetpack_submission1.domain.usecase

import androidx.lifecycle.LiveData
import com.example.jetpack_submission1.data.Resource
import com.example.jetpack_submission1.data.local.entity.MovieDetailEntity
import com.example.jetpack_submission1.data.local.entity.MovieDiscoverEntity
import com.example.jetpack_submission1.data.local.entity.TvDetailEntity
import com.example.jetpack_submission1.domain.model.MovieDiscover
import com.example.jetpack_submission1.domain.repository.IFilmRepository
import kotlinx.coroutines.flow.Flow

class FilmInteractor(private val filmRepository: IFilmRepository) : FilmUseCase {
    override fun getMovieDiscover() = filmRepository.getMovieDiscover()

    override fun getTvDiscover() = filmRepository.getTvDiscover()

    override fun getTvTrending(mediaType: String) = filmRepository.getTvTrending(mediaType)

    override fun getTrending(mediaType: String) = filmRepository.getTrending(mediaType)

    override fun getMovieDetail(movieId: String) = filmRepository.getMovieDetail(movieId)

    override fun getTvDetail(tvId: String) = filmRepository.getTvDetail(tvId)
}