package com.example.jetpack_submission1.viewmodel

import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class MovieDiscoverViewModelTest {
    private lateinit var viewModel: MovieDiscoverViewModel
    @Before
    fun setUp(){
        viewModel = MovieDiscoverViewModel()
    }
    @Test
    fun getData() {
        val discoverEntities = viewModel.getData()
        assertNotNull(discoverEntities)
    }
}