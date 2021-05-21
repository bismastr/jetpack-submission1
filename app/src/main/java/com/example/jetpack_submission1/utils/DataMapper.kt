package com.example.jetpack_submission1.utils

import com.example.jetpack_submission1.data.local.entity.MovieDiscoverEntity
import com.example.jetpack_submission1.data.local.entity.TvDiscoverEntity
import com.example.jetpack_submission1.data.remote.respone.MovieResultsItem
import com.example.jetpack_submission1.data.remote.respone.TrendingResultItems
import com.example.jetpack_submission1.data.remote.respone.TvResultsItem
import com.example.jetpack_submission1.domain.model.MovieDiscover

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
        input: List<TrendingResultItems>,
        mediaType: String
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
}