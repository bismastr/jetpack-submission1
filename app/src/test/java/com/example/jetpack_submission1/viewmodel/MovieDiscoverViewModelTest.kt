package com.example.jetpack_submission1.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.jetpack_submission1.data.Repository
import com.example.jetpack_submission1.data.local.entity.MovieDiscoverEntity
import com.example.jetpack_submission1.ui.movie.MovieViewModel
import com.example.jetpack_submission1.utils.DummyData
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MovieDiscoverViewModelTest {
//    private lateinit var viewModel: MovieDiscoverViewModel

    private lateinit var movieViewModel: MovieViewModel

    @get:Rule
    var instantTaskExcecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieRepository: Repository

    @Mock
    private lateinit var observer: Observer<List<MovieDiscoverEntity>>

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
        val dummyMovie = DummyData.generateDummyMovie()
        val movie = MutableLiveData<List<MovieDiscoverEntity>>()
        movie.value = dummyMovie

        `when`(movieRepository.getMovieDiscover()).thenReturn(movie)
        val movieEntities = movieViewModel.getMovieDiscover().value
        verify(movieRepository).getMovieDiscover()
        assertNotNull(movieEntities)

        movieViewModel.getMovieDiscover().observeForever(observer)
        verify(observer).onChanged(dummyMovie)
    }
}