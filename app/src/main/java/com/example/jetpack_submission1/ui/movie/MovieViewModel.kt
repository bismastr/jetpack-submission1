package com.example.jetpack_submission1.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.jetpack_submission1.data.Repository
import com.example.jetpack_submission1.data.local.entity.MovieDiscoverEntity

class MovieViewModel(private val repository: Repository): ViewModel() {

    fun getMovieDiscover(): LiveData<List<MovieDiscoverEntity>>{
        return repository.getMovieDiscover()
    }
}