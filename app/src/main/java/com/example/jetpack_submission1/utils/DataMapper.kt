package com.example.jetpack_submission1.utils

import com.example.jetpack_submission1.data.local.entity.MovieDiscoverEntity
import com.example.jetpack_submission1.data.remote.respone.MovieResultsItem
import com.example.jetpack_submission1.data.remote.respone.Response
import com.example.jetpack_submission1.domain.model.MovieDiscover

object DataMapper {
    fun filmResponseToEntities(input: List<MovieResultsItem>): List<MovieDiscoverEntity> {
        val filmList = ArrayList<MovieDiscoverEntity>()
        input.map {
            val movie = MovieDiscoverEntity(
                id = it.id,
                poster = it.posterPath,
                title = it.title,
                rating = it.voteAverage
            )
            filmList.add(movie)
        }
        return filmList
    }

    fun filmEntitiesToDomain(input: List<MovieDiscoverEntity>): List<MovieDiscover> {
        val filmList = ArrayList<MovieDiscover>()
        input.map {
            val movie = MovieDiscover(
                id = it.id,
                poster = it.poster,
                title = it.title,
                rating = it.rating
            )
            filmList.add(movie)
        }
        return filmList
    }
}