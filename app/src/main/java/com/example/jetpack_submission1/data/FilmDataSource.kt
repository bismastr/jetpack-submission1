package com.example.jetpack_submission1.data

import androidx.lifecycle.LiveData
import com.example.jetpack_submission1.data.local.entity.MovieDiscoverEntity
import com.example.jetpack_submission1.model.MovieResultsItem

interface FilmDataSource {

    fun getMovieDiscover(): LiveData<List<MovieResultsItem>>
}