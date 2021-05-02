package com.example.jetpack_submission1.utils

import com.example.jetpack_submission1.data.local.entity.MovieDiscoverEntity

object DummyData {

    fun generateDummyMovie(): ArrayList<MovieDiscoverEntity>{
        val movie = ArrayList<MovieDiscoverEntity>()

        movie.add(MovieDiscoverEntity(
            0,
        "Testing htpp",
        "Test title",
            8.1,
        ))

        movie.add(MovieDiscoverEntity(
            1,
        "Testing htpp",
        "Test title",
            8.2,
        ))

        movie.add(MovieDiscoverEntity(
            2,
            "Testing htpp",
            "Test title",
            8.3,
        ))

        movie.add(MovieDiscoverEntity(
            3,
            "Testing htpp",
            "Test title",
            8.4,
        ))

        movie.add(MovieDiscoverEntity(
            4,
            "Testing htpp",
            "Test title",
            8.5,
        ))

        return movie
    }
}