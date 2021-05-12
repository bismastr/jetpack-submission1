package com.example.jetpack_submission1.ui.notifications

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.jetpack_submission1.data.local.LocalRepository
import com.example.jetpack_submission1.data.local.entity.FavoriteEntity
import com.example.jetpack_submission1.data.local.entity.MovieDiscoverEntity

class FavoriteViewModel(private val localRepository: LocalRepository) : ViewModel() {


    fun getAllMovie(from: Int): LiveData<PagedList<FavoriteEntity>> = LivePagedListBuilder(localRepository.getAllMovie(from), 20).build()

}