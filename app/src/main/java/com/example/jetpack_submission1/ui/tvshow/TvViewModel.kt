package com.example.jetpack_submission1.ui.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.jetpack_submission1.data.Repository
import com.example.jetpack_submission1.data.local.entity.MovieDiscoverEntity
import com.example.jetpack_submission1.domain.usecase.FilmUseCase

class TvViewModel(private val filmUseCase: FilmUseCase) : ViewModel() {

    val tvDiscover = filmUseCase.getTvDiscover().asLiveData()


    val tvTrending = filmUseCase.getTvTrending("tv").asLiveData()
}