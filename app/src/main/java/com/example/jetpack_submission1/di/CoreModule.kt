package com.example.jetpack_submission1.di

import androidx.room.Room
import com.brillante.core.api.ApiService
import com.brillante.core.data.Repository
import com.brillante.core.data.local.LocalDataSource
import com.brillante.core.data.local.filmRoom.FilmDatabase
import com.brillante.core.data.remote.RemoteDataSource
import com.brillante.core.domain.repository.IFilmRepository
import com.brillante.core.utils.AppExecutors

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


val databaseModule = module {
    factory { get<FilmDatabase>().Dao() }
    single {
        Room.databaseBuilder(
            androidContext(),
            FilmDatabase::class.java,
            "movie_database"
        ).fallbackToDestructiveMigration().build()
    }
}

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE))
            .build()
    }

    single {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    factory { AppExecutors() }
    single<IFilmRepository> { Repository(get(), get(), get()) }
}
