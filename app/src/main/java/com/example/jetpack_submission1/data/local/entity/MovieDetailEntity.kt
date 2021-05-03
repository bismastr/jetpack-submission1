package com.example.jetpack_submission1.data.local.entity

data class MovieDetailEntity (
    var id: Int = 0,
    var title: String? = null,
    var poster: String? = null,
    var overview: String? = null,
    var release_date: String? = null,
    var rating: Double = 0.0,
        )