package com.example.jetpack_submission1.utils

import com.example.jetpack_submission1.data.local.entity.MovieDetailEntity
import com.example.jetpack_submission1.data.local.entity.MovieDiscoverEntity
import com.example.jetpack_submission1.data.local.entity.TvDetailEntity
import com.example.jetpack_submission1.data.local.entity.TvDiscoverEntity
import com.example.jetpack_submission1.data.remote.respone.*
import com.example.jetpack_submission1.domain.model.MovieDetail
import com.example.jetpack_submission1.domain.model.MovieDiscover
import com.example.jetpack_submission1.domain.model.TvDetail

object DataMapper {
    fun movieResponseToEntities(input: List<MovieResultsItem>): List<MovieDiscoverEntity> {
        val filmList = ArrayList<MovieDiscoverEntity>()
        input.map {
            val movie = MovieDiscoverEntity(
                id = it.id,
                poster = it.posterPath,
                title = it.title,
                rating = it.voteAverage,
                isTrending = false
            )
            filmList.add(movie)
        }
        return filmList
    }

    fun movieEntitiesToDomain(input: List<MovieDiscoverEntity>): List<MovieDiscover> {
        val filmList = ArrayList<MovieDiscover>()
        input.map {
            val movie = MovieDiscover(
                id = it.id,
                poster = it.poster,
                title = it.title,
                rating = it.rating,
                isTrending = it.isTrending
            )
            filmList.add(movie)
        }
        return filmList
    }

    fun trendingResponseToEntities(
        input: List<TrendingResultItems>
    ): List<MovieDiscoverEntity> {
        val filmList = ArrayList<MovieDiscoverEntity>()

        input.map {
            val movie = MovieDiscoverEntity(
                id = it.id,
                poster = it.backdropPath,
                title = it.title,
                rating = it.voteAverage,
                isTrending = true
            )
            filmList.add(movie)
        }



        return filmList
    }

    fun tvTrendingResponseToEntities(input: List<TrendingResultItems>): List<TvDiscoverEntity> {
        val filmList = ArrayList<TvDiscoverEntity>()
        input.map {
            val trending = TvDiscoverEntity(
                id = it.id,
                poster = it.backdropPath,
                title = it.originalName,
                rating = it.voteAverage,
                isTrending = true
            )
            filmList.add(trending)
        }
        return filmList
    }

    fun tvResponseToEntities(input: List<TvResultsItem>): List<TvDiscoverEntity> {
        val filmList = ArrayList<TvDiscoverEntity>()
        input.map {
            val movie = TvDiscoverEntity(
                id = it.id,
                poster = it.posterPath,
                title = it.originalName,
                rating = it.voteAverage,
                isTrending = false
            )
            filmList.add(movie)
        }
        return filmList
    }

    fun tvEntitiesToDomain(input: List<TvDiscoverEntity>): List<MovieDiscover> {
        val filmList = ArrayList<MovieDiscover>()
        input.map {
            val movie = MovieDiscover(
                id = it.id,
                poster = it.poster,
                title = it.title,
                rating = it.rating,
                isTrending = it.isTrending
            )
            filmList.add(movie)
        }
        return filmList
    }

    fun movieDetailResponseToEntities(input: DetailMovieResponse?): MovieDetailEntity {
        if (input != null) {
            return MovieDetailEntity(
                id = input.id,
                poster = input.posterPath,
                title = input.title,
                rating = input.voteAverage,
                overview = input.overview,
                release_date = input.releaseDate,
                )
        }
        return MovieDetailEntity()
    }

    fun movieDetailEntitiesToDomain(input: MovieDetailEntity?): MovieDetail {
        return if (input != null) {
            MovieDetail(
                id = input.id,
                poster = input.poster,
                title = input.title,
                rating = input.rating,
                overview = input.overview,
                release_date = input.release_date,
            )
        } else MovieDetail()

    }

    fun tvDetailResponseToEntities(response: DetailTvResponse?): TvDetailEntity {
        return if (response != null) {
            TvDetailEntity(
                id = response.id,
                numberEpisdoe = response.numberOfEpisodes,
                numberSeasons = response.numberOfSeasons,
                overview = response.overview,
                poster = response.posterPath,
                rating = response.voteAverage,
                title = response.originalName
            )
        } else TvDetailEntity()
    }

    fun tvDetailEntitiesToDomain(response: TvDetailEntity?): TvDetail {
        return if (response != null) {
            TvDetail(
                id = response.id,
                numberEpisode = response.numberEpisdoe,
                numberSeasons = response.numberSeasons,
                overview = response.overview,
                poster = response.poster,
                rating = response.rating,
                title = response.title
            )
        } else TvDetail()
    }
}