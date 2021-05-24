package com.example.jetpack_submission1.ui.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.brillante.core.domain.usecase.FilmUseCase

class MovieViewModel(filmUseCase: FilmUseCase) : ViewModel() {

    val movieDiscover = filmUseCase.getMovieDiscover().asLiveData()

    val movieTrending = filmUseCase.getTrending("movie").asLiveData()


}