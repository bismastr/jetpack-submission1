package com.example.jetpack_submission1.ui.notifications

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.jetpack_submission1.data.local.LocalRepository
import com.example.jetpack_submission1.data.local.entity.FavoriteEntity

class FavoriteViewModel(private val localRepository: LocalRepository) : ViewModel() {


    fun getAllMovie(from: Int): LiveData<PagedList<FavoriteEntity>> = localRepository.getAllMovie(from)

}