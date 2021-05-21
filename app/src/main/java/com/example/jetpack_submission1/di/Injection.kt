package com.example.jetpack_submission1.di

import android.content.Context
import com.example.jetpack_submission1.data.Repository
import com.example.jetpack_submission1.data.local.LocalDataSource
import com.example.jetpack_submission1.data.local.favoriteRoom.FavoriteDatabase
import com.example.jetpack_submission1.data.local.LocalRepository
import com.example.jetpack_submission1.data.local.filmRoom.FilmDatabase
import com.example.jetpack_submission1.data.remote.RemoteDataSource
import com.example.jetpack_submission1.domain.usecase.FilmInteractor
import com.example.jetpack_submission1.domain.usecase.FilmUseCase

object Injection {
    fun provideRepository(context: Context): Repository {
        val database = FilmDatabase.getDatabase(context)
        val remoteDataSource = RemoteDataSource.getInstance()
        val localDataSource = LocalDataSource.getInstance(database.Dao())
        return Repository.getInstance(remoteDataSource, localDataSource)
    }

    fun provideLocalRepository(context: Context): LocalRepository{
        val database = FavoriteDatabase.getDatabase(context)
        return LocalRepository.getInstance(database.filmDao())
    }

    fun provideTourismUseCase(context: Context): FilmUseCase {
        val repository = provideRepository(context)
        return FilmInteractor(repository)
    }


}