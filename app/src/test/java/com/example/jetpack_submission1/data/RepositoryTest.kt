package com.example.jetpack_submission1.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.jetpack_submission1.data.remote.RemoteDataSource
import com.example.jetpack_submission1.utils.DummyData
import com.example.jetpack_submission1.utils.LiveDataTestUtil
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.doAnswer
import org.mockito.Mockito.mock


class RepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = mock(RemoteDataSource::class.java)
    private val repository = FakeRepository(remote)


    private val movieDiscoverResponse = DummyData.generateRemoteMovieDiscover()
    private val trendingResponse = DummyData.generateRemoteTrending()
    private val tvDiscoverResponse = DummyData.generateRemoteTvDiscover()
    private val tvDetailResponse = DummyData.generateRemoteTvDetail()
    private val movieDetailResponse = DummyData.generateRemoteMovieDetail()

    @Test
    fun getAllTrending(){
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadTrendingCallback)
                .onAllTrendingReceived(trendingResponse)
            null
        }.`when`(remote).getTrending(any(), eq("tv"))

        val trendingEntities = LiveDataTestUtil.getValue(repository.getTrending("tv"))

        verify(remote).getTrending(any(), eq("tv"))

        assertNotNull(trendingEntities)
        assertEquals(trendingResponse.size.toLong(), trendingEntities.size.toLong())
    }

   @Test
   fun getMovieDiscover(){
       doAnswer { invocation ->
           (invocation.arguments[0] as RemoteDataSource.LoadMovieCallback)
               .onAllMovieReceived(movieDiscoverResponse)
           null
       }.`when`(remote).getDiscoverMovie(any())

       val movieDiscoverEntities = LiveDataTestUtil.getValue(repository.getMovieDiscover())

       verify(remote).getDiscoverMovie(any())

       assertNotNull(movieDiscoverEntities)
       assertEquals(movieDiscoverResponse.size.toLong(), movieDiscoverEntities.size.toLong())
   }

    @Test
    fun getMovieDetail(){
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadDetailCallback)
                .onAllDetailReceived(movieDetailResponse)
            null
        }.`when`(remote).getDetailMovie(any(), eq("0"))

        val movieDetailEntities = LiveDataTestUtil.getValue(repository.getMovieDetail("0"))

        verify(remote).getDetailMovie(any(), eq("0"))

        assertNotNull(movieDetailEntities)
    }

    @Test
    fun getTvDiscover(){
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadTvCallback)
                .onAllTvReceived(tvDiscoverResponse)
            null
        }.`when`(remote).getDiscoverTv(any())

        val tvDiscoverEntities = LiveDataTestUtil.getValue(repository.getTvDiscover())

        verify(remote).getDiscoverTv(any())

        assertNotNull(tvDiscoverEntities)
        assertEquals(tvDiscoverResponse.size.toLong(), tvDiscoverEntities.size.toLong())
    }

    @Test
    fun getTvDetail(){
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadDetailTvCallback)
                .onAllDetailTvReceived(tvDetailResponse)
            null
        }.`when`(remote).getDetailTv(any(), eq("0"))

        val tvDetailEntities = LiveDataTestUtil.getValue(repository.getTvDetail("0"))

        verify(remote).getDetailTv(any(), eq("0"))

        assertNotNull(tvDetailEntities)
    }








}