package com.example.jetpack_submission1.di

import androidx.room.Room
import com.example.jetpack_submission1.api.ApiService
import com.example.jetpack_submission1.data.Repository
import com.example.jetpack_submission1.data.local.LocalDataSource
import com.example.jetpack_submission1.data.local.filmRoom.FilmDatabase
import com.example.jetpack_submission1.data.remote.RemoteDataSource
import com.example.jetpack_submission1.domain.repository.IFilmRepository
import com.example.jetpack_submission1.utils.AppExecutors
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
