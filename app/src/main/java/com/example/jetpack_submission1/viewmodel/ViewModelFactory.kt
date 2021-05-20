package com.example.jetpack_submission1.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.jetpack_submission1.data.Repository
import com.example.jetpack_submission1.data.local.LocalRepository
import com.example.jetpack_submission1.di.Injection
import com.example.jetpack_submission1.domain.usecase.FilmUseCase
import com.example.jetpack_submission1.ui.detail.DetailViewModel
import com.example.jetpack_submission1.ui.movie.MovieViewModel
import com.example.jetpack_submission1.ui.favorite.FavoriteViewModel
import com.example.jetpack_submission1.ui.tvshow.TvViewModel

class ViewModelFactory private constructor(private val mFilmUseCase: FilmUseCase, private val localRepository: LocalRepository): ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this){
                instance ?: ViewModelFactory(Injection.provideTourismUseCase(), Injection.provideLocalRepository(context)).apply {
                    instance = this
                }
            }


    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MovieViewModel::class.java) -> {
                 MovieViewModel(mFilmUseCase) as T
            }
            modelClass.isAssignableFrom(TvViewModel::class.java) -> {
                 TvViewModel(mFilmUseCase) as T
            }
            modelClass.isAssignableFrom(DetailViewModel::class.java) -> {
                DetailViewModel(mFilmUseCase, localRepository) as T
            }
            modelClass.isAssignableFrom(FavoriteViewModel::class.java) -> {
                FavoriteViewModel(localRepository) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
    }
}