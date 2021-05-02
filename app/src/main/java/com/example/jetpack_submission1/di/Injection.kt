package com.example.jetpack_submission1.di

import com.example.jetpack_submission1.data.Repository
import com.example.jetpack_submission1.data.remote.RemoteDataSource

object Injection {
    fun provideRepository(): Repository{
        val remoteDataSource = RemoteDataSource.getInstance()
        return Repository.getInstance(remoteDataSource)
    }
}