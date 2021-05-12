package com.example.jetpack_submission1.di

import android.app.Application
import android.content.Context
import com.example.jetpack_submission1.data.Repository
import com.example.jetpack_submission1.data.local.FavoriteDatabase
import com.example.jetpack_submission1.data.local.LocalRepository
import com.example.jetpack_submission1.data.remote.RemoteDataSource

object Injection {
    fun provideRepository(): Repository{

        val remoteDataSource = RemoteDataSource.getInstance()
        return Repository.getInstance(remoteDataSource)
    }

    fun provideLocalRepository(context: Context): LocalRepository{
        val database = FavoriteDatabase.getDatabase(context)
        return LocalRepository.getInstance(database.filmDao())
    }


}