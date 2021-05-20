package com.example.jetpack_submission1.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.jetpack_submission1.data.Repository
import com.example.jetpack_submission1.data.local.entity.MovieDiscoverEntity
import com.example.jetpack_submission1.domain.usecase.FilmUseCase

class MovieViewModel(private val filmUseCase: FilmUseCase): ViewModel() {

    fun getMovieDiscover(): LiveData<List<MovieDiscoverEntity>>{
        return filmUseCase.getMovieDiscover()
    }

    fun getMovieTrending(): LiveData<List<MovieDiscoverEntity>>{
        return filmUseCase.getTrending("movie")
    }


}