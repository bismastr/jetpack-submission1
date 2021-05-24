package com.example.jetpack_submission1.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.brillante.core.domain.model.MovieDetail
import com.brillante.core.domain.model.TvDetail
import com.brillante.core.domain.usecase.FilmUseCase

class DetailViewModel(
    private val filmUseCase: FilmUseCase
) : ViewModel() {

    fun getMovieDetail(movieId: String) = filmUseCase.getMovieDetail(movieId).asLiveData()


    fun getTvDetail(tvId: String) = filmUseCase.getTvDetail(tvId).asLiveData()

    fun setMovieFavorite(film: MovieDetail, state: Boolean) =
        filmUseCase.setMovieFavorite(film, state)

    fun setTvFavorite(film: TvDetail, state: Boolean) =
        filmUseCase.setTvFavorite(film, state)
}