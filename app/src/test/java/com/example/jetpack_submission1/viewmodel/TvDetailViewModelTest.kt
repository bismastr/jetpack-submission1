package com.example.jetpack_submission1.viewmodel

import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class TvDetailViewModelTest {
    private lateinit var viewModel: TvDetailViewModel
    @Before
    fun setUp(){
        viewModel = TvDetailViewModel()
    }
    @Test
    fun getData() {
        val discoverEntities = viewModel.getData()
        assertNotNull(discoverEntities)
    }
}