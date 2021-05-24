package com.example.jetpack_submission1.ui.tvshow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.brillante.core.domain.usecase.FilmUseCase

class TvViewModel(filmUseCase: FilmUseCase) : ViewModel() {

    val tvDiscover = filmUseCase.getTvDiscover().asLiveData()

    val tvTrending = filmUseCase.getTvTrending("tv").asLiveData()
}