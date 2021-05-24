package com.example.jetpack_submission1.ui.tv

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.jetpack_submission1.data.Repository
import com.example.jetpack_submission1.data.local.entity.MovieDiscoverEntity
import com.example.jetpack_submission1.ui.tvshow.TvViewModel
import com.example.jetpack_submission1.utils.DummyData
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TvDiscoverViewModelTest {
    private lateinit var viewModel: TvViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: Repository

    @Mock
    private lateinit var observer: Observer<List<MovieDiscoverEntity>>

    @Before
    fun setUp() {
        viewModel = TvViewModel(repository)
    }

    @Test
    fun getDiscover() {
        val dummyData = DummyData.generateDummyMovie()
        val data = MutableLiveData<List<MovieDiscoverEntity>>()
        data.value = dummyData

        `when`(repository.getTvDiscover()).thenReturn(data)
        val dataEntities = viewModel.getTvDiscover().value
        verify(repository).getTvDiscover()
        assertNotNull(dataEntities)
        assertEquals(5, dataEntities.size)

        viewModel.getTvDiscover().observeForever(observer)
        verify(observer).onChanged(dummyData)
    }

    @Test
    fun getTrending() {
        val dummyData = DummyData.generateDummyMovie()
        val data = MutableLiveData<List<MovieDiscoverEntity>>()
        data.value = dummyData

        `when`(repository.getTrending("tv")).thenReturn(data)
        val dataEntities = viewModel.getTvTrending().value
        verify(repository).getTrending("tv")
        assertNotNull(dataEntities)
        assertEquals(5, dataEntities.size)

        viewModel.getTvTrending().observeForever(observer)
        verify(observer).onChanged(dummyData)
    }
}