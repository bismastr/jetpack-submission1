package com.example.jetpack_submission1.ui.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.jetpack_submission1.data.Repository
import com.example.jetpack_submission1.data.local.entity.MovieDiscoverEntity

class TvViewModel(private val repository: Repository): ViewModel() {
    fun getTvDiscover(): LiveData<List<MovieDiscoverEntity>>{
        return repository.getTvDiscover()
    }

    fun getTvTrending(): LiveData<List<MovieDiscoverEntity>>{
        return repository.getTrending("tv")
    }
}