package com.example.jetpack_submission1.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.jetpack_submission1.data.local.entity.MovieDiscoverEntity
import com.example.jetpack_submission1.domain.usecase.FilmUseCase

class MovieViewModel(private val filmUseCase: FilmUseCase) : ViewModel() {

    val movieDiscover = filmUseCase.getMovieDiscover().asLiveData()


    fun getMovieTrending(): LiveData<List<MovieDiscoverEntity>> {
        return filmUseCase.getTrending("movie")
    }


}