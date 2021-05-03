package com.example.jetpack_submission1.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.jetpack_submission1.data.Repository
import com.example.jetpack_submission1.di.Injection
import com.example.jetpack_submission1.ui.detail.DetailViewModel
import com.example.jetpack_submission1.ui.movie.MovieViewModel
import com.example.jetpack_submission1.ui.tvshow.TvViewModel

class ViewModelFactory private constructor(private val mRepository: Repository): ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this){
                instance ?: ViewModelFactory(Injection.provideRepository()).apply {
                    instance = this
                }
            }


    }



    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MovieViewModel::class.java) -> {
                 MovieViewModel(mRepository) as T
            }
            modelClass.isAssignableFrom(TvViewModel::class.java) -> {
                 TvViewModel(mRepository) as T
            }
            modelClass.isAssignableFrom(DetailViewModel::class.java) -> {
                DetailViewModel(mRepository) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
    }
}