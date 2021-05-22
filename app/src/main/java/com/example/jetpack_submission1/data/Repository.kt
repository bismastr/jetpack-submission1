package com.example.jetpack_submission1.data

import com.example.jetpack_submission1.api.ApiResponse
import com.example.jetpack_submission1.data.local.LocalDataSource
import com.example.jetpack_submission1.data.remote.RemoteDataSource
import com.example.jetpack_submission1.data.remote.respone.*
import com.example.jetpack_submission1.domain.model.MovieDetail
import com.example.jetpack_submission1.domain.model.MovieDiscover
import com.example.jetpack_submission1.domain.model.TvDetail
import com.example.jetpack_submission1.domain.repository.IFilmRepository
import com.example.jetpack_submission1.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class Repository private constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) :
    IFilmRepository {

    companion object {
        @Volatile
        private var instance: Repository? = null

        fun getInstance(
            remoteDataSource: RemoteDataSource,
            localDataSource: LocalDataSource
        ): Repository =
            instance ?: synchronized(this) {
                instance ?: Repository(remoteDataSource, localDataSource).apply { instance = this }
            }


    }

    //MovieDiscover
    override fun getMovieDiscover(): Flow<Resource<List<MovieDiscover>>> =
        object : NetworkBoundResource<List<MovieDiscover>, List<MovieResultsItem>>() {
            override fun loadFromDB(): Flow<List<MovieDiscover>> {
                return localDataSource.getAllMovieDiscover().map {
                    DataMapper.movieEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<MovieDiscover>?): Boolean {
                return true
            }

            override suspend fun createCall(): Flow<ApiResponse<List<MovieResultsItem>>> {
                return remoteDataSource.getDiscoverMovie()
            }

            override suspend fun saveCallResult(data: List<MovieResultsItem>) {
                val filmList = DataMapper.movieResponseToEntities(data)
                localDataSource.insertMovieDiscover(filmList)
            }


        }.asFlow()

    //MovieTrending
    override fun getTrending(mediaType: String): Flow<Resource<List<MovieDiscover>>> =
        object : NetworkBoundResource<List<MovieDiscover>, List<TrendingResultItems>>() {
            override fun loadFromDB(): Flow<List<MovieDiscover>> {
                return localDataSource.getAllMovieTrending().map {
                    DataMapper.movieEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<MovieDiscover>?): Boolean {
                return true
            }

            override suspend fun createCall(): Flow<ApiResponse<List<TrendingResultItems>>> {
                return remoteDataSource.getTrending(mediaType)
            }

            override suspend fun saveCallResult(data: List<TrendingResultItems>) {
                val trendingList = DataMapper.trendingResponseToEntities(data)
                localDataSource.insertMovieTrending(trendingList)
            }

        }.asFlow()

    //TvDiscover
    override fun getTvDiscover(): Flow<Resource<List<MovieDiscover>>> =
        object : NetworkBoundResource<List<MovieDiscover>, List<TvResultsItem>>() {
            override fun loadFromDB(): Flow<List<MovieDiscover>> {
                return localDataSource.getAllTvDiscover().map {
                    DataMapper.tvEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<MovieDiscover>?): Boolean {
                return true
            }

            override suspend fun createCall(): Flow<ApiResponse<List<TvResultsItem>>> {
                return remoteDataSource.getDiscoverTv()
            }

            override suspend fun saveCallResult(data: List<TvResultsItem>) {
                val tvList = DataMapper.tvResponseToEntities(data)
                localDataSource.insertTvDiscover(tvList)
            }

        }.asFlow()

    override fun getTvTrending(mediaType: String): Flow<Resource<List<MovieDiscover>>> =
        object : NetworkBoundResource<List<MovieDiscover>, List<TrendingResultItems>>() {
            override fun loadFromDB(): Flow<List<MovieDiscover>> {
                return localDataSource.getAllTvTrending().map {
                    DataMapper.tvEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<MovieDiscover>?): Boolean {
                return true
            }

            override suspend fun createCall(): Flow<ApiResponse<List<TrendingResultItems>>> {
                return remoteDataSource.getTrending(mediaType)
            }

            override suspend fun saveCallResult(data: List<TrendingResultItems>) {
                val trendingList = DataMapper.tvTrendingResponseToEntities(data)
                localDataSource.insertTvTrending(trendingList)
            }

        }.asFlow()

    override fun getMovieDetail(movieId: String): Flow<Resource<MovieDetail>> =
        object : NetworkBoundResource<MovieDetail, DetailMovieResponse?>() {
            override fun loadFromDB(): Flow<MovieDetail> {
                return localDataSource.getMovieDetail(movieId).map{
                    DataMapper.movieDetailEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: MovieDetail?): Boolean {
                return true
            }

            override suspend fun createCall(): Flow<ApiResponse<DetailMovieResponse>> {
                return remoteDataSource.getDetailMovie(movieId)
            }

            override suspend fun saveCallResult(data: DetailMovieResponse?) {
                val detail = DataMapper.movieDetailResponseToEntities(data)
                localDataSource.insertMovieDetail(detail)
            }

        }.asFlow()

    override fun getTvDetail(tvId: String): Flow<Resource<TvDetail>> =
        object : NetworkBoundResource<TvDetail, DetailTvResponse?>(){
            override fun loadFromDB(): Flow<TvDetail> {
                return localDataSource.getTvDetail(tvId).map {
                    DataMapper.tvDetailEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: TvDetail?): Boolean {
                return true
            }

            override suspend fun createCall(): Flow<ApiResponse<DetailTvResponse?>> {
                return remoteDataSource.getDetailTv(tvId)
            }

            override suspend fun saveCallResult(data: DetailTvResponse?) {
                val detail = DataMapper.tvDetailResponseToEntities(data)
                localDataSource.insertTvDetail(detail)
            }

        }.asFlow()


}