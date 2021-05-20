package com.example.jetpack_submission1.di

import android.content.Context
import com.example.jetpack_submission1.data.Repository
import com.example.jetpack_submission1.data.local.FavoriteDatabase
import com.example.jetpack_submission1.data.local.LocalRepository
import com.example.jetpack_submission1.data.remote.RemoteDataSource
import com.example.jetpack_submission1.domain.usecase.FilmInteractor
import com.example.jetpack_submission1.domain.usecase.FilmUseCase

object Injection {
    fun provideRepository(): com.example.jetpack_submission1.data.Repository {

        val remoteDataSource = RemoteDataSource.getInstance()
        return Repository.getInstance(remoteDataSource)
    }

    fun provideLocalRepository(context: Context): LocalRepository{
        val database = FavoriteDatabase.getDatabase(context)
        return LocalRepository.getInstance(database.filmDao())
    }

    fun provideTourismUseCase(): FilmUseCase {
        val repository = provideRepository()
        return FilmInteractor(repository)
    }


}