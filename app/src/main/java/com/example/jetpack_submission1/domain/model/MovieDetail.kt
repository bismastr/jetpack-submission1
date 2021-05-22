package com.example.jetpack_submission1.domain.model

data class MovieDetail (
    val id: Int = 0,
    val title: String? = null,
    val poster: String? = null,
    val overview: String? = null,
    val release_date: String? = null,
    val rating: Double = 0.0,
)