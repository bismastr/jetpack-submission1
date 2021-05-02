package com.example.jetpack_submission1.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.jetpack_submission1.data.Repository
import com.example.jetpack_submission1.data.remote.RemoteDataSource
import com.example.jetpack_submission1.model.MovieResultsItem
import com.example.jetpack_submission1.ui.movie.MovieViewModel
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MovieDiscoverViewModelTest {
//    private lateinit var viewModel: MovieDiscoverViewModel
    private val remote = Mockito.mock(RemoteDataSource::class.java)
    private lateinit var movieViewModel: MovieViewModel

    @Mock
    private lateinit var movieRepository: Repository

    @Before
    fun setUp(){
        movieViewModel = MovieViewModel(movieRepository)
    }

//    @Test
//    fun getData() {
//        val discoverEntities = viewModel.getData()
//        assertNotNull(discoverEntities)
//    }
    @Test
    fun getMovie(){
        `when`(remote.getDiscoverMovie()).thenReturn(null)
        val movieData = movieViewModel.getMovieDiscover()
        verify<RemoteDataSource>(remote).getDiscoverMovie()
    }
}