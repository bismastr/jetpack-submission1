package com.example.jetpack_submission1.viewmodel

import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class MovieTrendingViewModelTest {
    private lateinit var viewModel: MovieTrendingViewModel

    @Before
    fun setUp(){
        viewModel = MovieTrendingViewModel()
    }
    @Test
    fun getData() {
        val trendingEntities = viewModel.getData()
        assertNotNull(trendingEntities)
    }
}