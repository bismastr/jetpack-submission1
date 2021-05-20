package com.example.jetpack_submission1.domain.usecase

import androidx.lifecycle.LiveData
import com.example.jetpack_submission1.data.local.entity.MovieDetailEntity
import com.example.jetpack_submission1.data.local.entity.MovieDiscoverEntity
import com.example.jetpack_submission1.data.local.entity.TvDetailEntity
import com.example.jetpack_submission1.domain.repository.IFilmRepository

class FilmInteractor(private val filmRepository: IFilmRepository): FilmUseCase {
    override fun getMovieDiscover(): LiveData<List<MovieDiscoverEntity>> {
        return filmRepository.getMovieDiscover()
    }

    override fun getTvDiscover(): LiveData<List<MovieDiscoverEntity>> {
        return filmRepository.getTvDiscover()
    }

    override fun getTrending(mediaType: String): LiveData<List<MovieDiscoverEntity>> {
        return filmRepository.getTrending(mediaType)
    }

    override fun getMovieDetail(movieId: String): LiveData<MovieDetailEntity> {
        return filmRepository.getMovieDetail(movieId)
    }

    override fun getTvDetail(tvId: String): LiveData<TvDetailEntity> {
        return filmRepository.getTvDetail(tvId)
    }
}