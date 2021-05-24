package com.example.jetpack_submission1.ui.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.jetpack_submission1.domain.usecase.FilmUseCase

class FavoriteViewModel(private val filmUseCase: FilmUseCase) : ViewModel() {

    fun getMovieFavorite() = filmUseCase.getMovieFavorite().asLiveData()

    fun getTvFavorite() = filmUseCase.getTvFavorite().asLiveData()

}