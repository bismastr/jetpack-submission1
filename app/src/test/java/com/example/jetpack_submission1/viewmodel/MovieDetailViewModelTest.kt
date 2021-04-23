package com.example.jetpack_submission1.viewmodel

import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class MovieDetailViewModelTest {
    private lateinit var viewModel: MovieDetailViewModel

    @Before
    fun setUp(){
        viewModel = MovieDetailViewModel()
    }
    @Test
    fun getData() {
        val detailEntities = viewModel.getData()
        assertNotNull(detailEntities)
    }
}