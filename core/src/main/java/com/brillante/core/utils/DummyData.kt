package com.brillante.core.utils

import com.brillante.core.data.local.entity.MovieDiscoverEntity
import com.brillante.core.data.remote.respone.DetailMovieResponse
import com.brillante.core.data.remote.respone.MovieResultsItem
import com.brillante.core.data.remote.respone.TrendingResultItems
import com.brillante.core.data.remote.respone.TvResultsItem


object DummyData {

    fun generateDummyMovie(): ArrayList<MovieDiscoverEntity> {
        val movie = ArrayList<MovieDiscoverEntity>()

        movie.add(
            MovieDiscoverEntity(
                0,
                "Testing htpp",
                "Test title",
                8.1,
            )
        )

        movie.add(
            MovieDiscoverEntity(
                1,
                "Testing htpp",
                "Test title",
                8.2,
            )
        )

        movie.add(
            MovieDiscoverEntity(
                2,
                "Testing htpp",
                "Test title",
                8.3,
            )
        )

        movie.add(
            MovieDiscoverEntity(
                3,
                "Testing htpp",
                "Test title",
                8.4,
            )
        )

        movie.add(
            MovieDiscoverEntity(
                4,
                "Testing htpp",
                "Test title",
                8.5,
            )
        )

        return movie
    }

//    fun generateDummyDetail(): MovieDetailEntity {
//        val detail = MovieDetailEntity()
//        detail.id = 0
//        detail.release_date = "Today"
//        detail.title = "Movie Title"
//        detail.rating = 8.4
//        detail.poster = "poster Link"
//        detail.overview = "Overview"
//
//        return detail
//    }

//    fun generateTvDummyDetail(): TvDetailEntity {
//        val detail = TvDetailEntity()
//        detail.id = 0
//        detail.numberSeasons = 10
//        detail.numberEpisdoe = 10
//        detail.title = "Movie Title"
//        detail.rating = 8.4
//        detail.poster = "poster Link"
//        detail.overview = "Overview"
//
//        return detail
//    }

    fun generateRemoteTvDiscover(): ArrayList<TvResultsItem> {
        val resultItems = TvResultsItem(
            "first_air_date",
            "Overview",
            "Original language",
            null,
            "poster_path",
            null,
            "backdrop",
            80.30,
            8.4,
            "originalName",
            "name",
            1,
            1000,
        )

        val result = ArrayList<TvResultsItem>()
        result.add(resultItems)

        return result

    }


    fun generateRemoteMovieDiscover(): ArrayList<MovieResultsItem> {
        val resultItems = MovieResultsItem(
            "Overview",
            "Original language",
            "original_title",
            false,
            "title",
            null,
            "poster_path",
            "backdrop_path",
            "release_date",
            100.100,
            8.4,
            0,
            false,
            10000
        )
        val result = ArrayList<MovieResultsItem>()
        result.add(resultItems)

        return result
    }

    fun generateRemoteTrending(): ArrayList<TrendingResultItems> {
        val resultItems = TrendingResultItems(
            "Overview",
            "Original language",
            "Original Title",
            false,
            "title",
            null,
            "poster_path",
            "backdrop_path",
            "release_date",
            8.4,
            100.100,
            0,
            false,
            0,
            "firstAirDate",
            null,
            "original_name",
            "name"
        )

        val result = ArrayList<TrendingResultItems>()
        result.add(resultItems)

        return result

    }

//    fun generateRemoteTvDetail(): DetailTvResponse {
//        return DetailTvResponse(
//            "original_language",
//            10,
//            null,
//            "type",
//            "backdrop_path",
//            null,
//            100.100,
//            null,
//            0,
//            10,
//            1000,
//            "first_air_date",
//            "overview",
//            null,
//            null,
//            null,
//            null,
//            "poster_path",
//            null,
//            null,
//            null,
//            "original_name",
//            8.4,
//            "name",
//            "tagline",
//            null,
//            null,
//            false,
//            "last_air_date",
//            "homepage",
//            "status"
//
//        )
//    }

    fun generateRemoteMovieDetail(): DetailMovieResponse {
        return DetailMovieResponse(
            "original_language",
            "imdb",
            false,
            "title",
            "backddrop",
            0,
            null,
            200.200,
            null,
            0,
            1000,
            10000000,
            "overview",
            "originalTitle",
            1000,
            "posterPath",
            null,
            null,
            "relaseDate",
            8.4,
            "belongs",
            "tagLine",
            false,
            "homePage",
            "status"
        )
    }
}