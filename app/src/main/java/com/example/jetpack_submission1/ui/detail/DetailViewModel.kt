package com.example.jetpack_submission1.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.jetpack_submission1.data.Repository
import com.example.jetpack_submission1.data.local.LocalRepository
import com.example.jetpack_submission1.data.local.entity.FavoriteEntity
import com.example.jetpack_submission1.data.local.entity.MovieDetailEntity
import com.example.jetpack_submission1.data.local.entity.TvDetailEntity
import com.example.jetpack_submission1.domain.model.MovieDetail
import com.example.jetpack_submission1.domain.usecase.FilmUseCase
import kotlinx.coroutines.launch

class DetailViewModel(
    private val filmUseCase: FilmUseCase,
    private val localRepository: LocalRepository
) : ViewModel() {

//    private val localRepository: LocalRepository = LocalRepository(application)

    fun getMovieDetail(movieId: String) = filmUseCase.getMovieDetail(movieId).asLiveData()



    fun getTvDetail(tvId: String) = filmUseCase.getTvDetail(tvId).asLiveData()


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