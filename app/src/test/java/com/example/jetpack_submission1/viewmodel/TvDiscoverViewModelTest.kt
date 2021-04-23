package com.example.jetpack_submission1.viewmodel

import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class TvDiscoverViewModelTest {
    private lateinit var viewModel: TvDiscoverViewModel

    @Before
    fun setUp(){
        viewModel = TvDiscoverViewModel()
    }
    @Test
    fun getData() {
        val tvDiscoverEntities = viewModel.getData()
        assertNotNull(tvDiscoverEntities)
    }
}