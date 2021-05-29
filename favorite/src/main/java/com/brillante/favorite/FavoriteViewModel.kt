package com.brillante.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.brillante.core.domain.usecase.FilmUseCase

class FavoriteViewModel(private val filmUseCase: FilmUseCase) : ViewModel() {

    fun getMovieFavorite() = filmUseCase.getMovieFavorite().asLiveData()

    fun getTvFavorite() = filmUseCase.getTvFavorite().asLiveData()

}