package com.example.jetpack_submission1.ui.detail

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpack_submission1.data.Repository
import com.example.jetpack_submission1.data.local.LocalRepository
import com.example.jetpack_submission1.data.local.entity.FavoriteEntity
import com.example.jetpack_submission1.data.local.entity.MovieDetailEntity
import com.example.jetpack_submission1.data.local.entity.TvDetailEntity
import kotlinx.coroutines.launch

class DetailViewModel(
    private val repository: Repository,
    private val localRepository: LocalRepository
) : ViewModel() {

//    private val localRepository: LocalRepository = LocalRepository(application)

    fun getMovieDetail(movieId: String): LiveData<MovieDetailEntity> {
        return repository.getMovieDetail(movieId)
    }

    fun getTvDetail(tvId: String): LiveData<TvDetailEntity> {
        return repository.getTvDetail(tvId)
    }

    fun insert(favoriteEntity: FavoriteEntity) {
        viewModelScope.launch {
            localRepository.insert(favoriteEntity)
        }
    }

    fun delete(favoriteEntity: FavoriteEntity) {
        viewModelScope.launch {
            localRepository.delete(favoriteEntity)
        }
    }

    suspend fun isChecked(id: Int): Boolean {
        val count: Int = localRepository.checkFavorite(id)
        return count > 0
    }
}