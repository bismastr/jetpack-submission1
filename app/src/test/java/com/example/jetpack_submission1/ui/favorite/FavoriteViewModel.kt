package com.example.jetpack_submission1.ui.favorite

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.DataSource
import com.example.jetpack_submission1.data.local.favoriteRoom.FilmDao
import com.example.jetpack_submission1.data.local.LocalRepository
import com.example.jetpack_submission1.data.local.entity.FavoriteEntity
import com.nhaarman.mockitokotlin2.verify
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FavoriteViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private  val mDao = mock(FilmDao::class.java)
    private val repository = LocalRepository(mDao)
    private  val  viewModel = FavoriteViewModel(repository)


    @Mock
    private lateinit var dataSourceFactory: DataSource.Factory<Int, FavoriteEntity>

    @Test
    fun favorite(){
        `when`(mDao.getFavorite(1)).thenReturn(dataSourceFactory)
        viewModel.getAllMovie(1)
        verify(mDao).getFavorite(1)
    }
}