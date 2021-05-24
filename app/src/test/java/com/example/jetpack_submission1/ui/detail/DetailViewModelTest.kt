package com.example.jetpack_submission1.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.jetpack_submission1.data.Repository
import com.example.jetpack_submission1.data.local.entity.FavoriteEntity
import com.example.jetpack_submission1.data.local.entity.MovieDetailEntity
import com.example.jetpack_submission1.data.local.entity.TvDetailEntity
import com.example.jetpack_submission1.data.local.favoriteRoom.FilmDao
import com.example.jetpack_submission1.utils.DummyData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
@ExperimentalCoroutinesApi
class DetailViewModelTest {
    private lateinit var detailViewModel: DetailViewModel
    private val testCoroutineDispatcher = TestCoroutineDispatcher()
    private val mDao = Mockito.mock(FilmDao::class.java)

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: Repository


    @Mock
    private val localRepository = LocalRepository(mDao)

    @Mock
    private lateinit var observerMovie: Observer<MovieDetailEntity>

    @Mock
    private lateinit var observerTv: Observer<TvDetailEntity>

    @Before
    fun setUp() {
        Dispatchers.setMain(testCoroutineDispatcher)
        detailViewModel = DetailViewModel(repository, localRepository)
    }


    @Test
    fun getDetailMovie() {
        val dummyMovie = DummyData.generateDummyDetail()
        val movie = MutableLiveData<MovieDetailEntity>()
        movie.value = dummyMovie

        `when`(repository.getMovieDetail("0")).thenReturn(movie)
        val movieEntities = detailViewModel.getMovieDetail("0").value
        verify(repository).getMovieDetail("0")
        Assert.assertNotNull(movieEntities)

        detailViewModel.getMovieDetail("0").observeForever(observerMovie)
        verify(observerMovie).onChanged(dummyMovie)
    }

    @Test
    fun getDetailTv() {
        val dummyTv = DummyData.generateTvDummyDetail()
        val tv = MutableLiveData<TvDetailEntity>()
        tv.value = dummyTv

        `when`(repository.getTvDetail("0")).thenReturn(tv)
        val movieEntities = detailViewModel.getTvDetail("0").value
        verify(repository).getTvDetail("0")
        Assert.assertNotNull(movieEntities)

        detailViewModel.getTvDetail("0").observeForever(observerTv)
        verify(observerTv).onChanged(dummyTv)
    }

    @Test
    fun insert() {
        val favoriteEntity =
            FavoriteEntity(
                id = 1,
                poster = "/woqejwiq.com",
                title = "Best Film",
                rating = 8.4,
                from = 1
            )


        runBlocking {
            detailViewModel.insert(favoriteEntity)
            verify(localRepository).insert(favoriteEntity)
        }
    }

    @Test
    fun delete() {
        val favoriteEntity =
            FavoriteEntity(
                id = 1,
                poster = "/woqejwiq.com",
                title = "Best Film",
                rating = 8.4,
                from = 1
            )

        runBlocking {
            detailViewModel.delete(favoriteEntity)
            verify(localRepository).delete(favoriteEntity)
        }
    }
}