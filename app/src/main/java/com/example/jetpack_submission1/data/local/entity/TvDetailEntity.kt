package com.example.jetpack_submission1.data.local.entity

data class TvDetailEntity(
    var id: Int = 0,
    var title: String? = null,
    var numberEpisdoe: Int = 0,
    var numberSeasons: Int = 0,
    var poster: String? = null,
    var overview: String? = null,
    var rating: Double = 0.0
)