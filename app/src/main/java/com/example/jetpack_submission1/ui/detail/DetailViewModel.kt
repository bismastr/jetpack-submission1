package com.example.jetpack_submission1.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.jetpack_submission1.data.Repository
import com.example.jetpack_submission1.data.local.entity.MovieDetailEntity
import com.example.jetpack_submission1.data.local.entity.TvDetailEntity

class DetailViewModel(private val repository: Repository): ViewModel() {

    fun getMovieDetail(movieId: String): LiveData<MovieDetailEntity>{
        return repository.getMovieDetail(movieId)
    }

    fun getTvDetail(tvId: String): LiveData<TvDetailEntity>{
        return repository.getTvDetail(tvId)
    }
}