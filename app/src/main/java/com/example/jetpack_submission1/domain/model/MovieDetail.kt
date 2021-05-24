package com.example.jetpack_submission1.domain.model

data class MovieDetail(
    val id: Int,
    val title: String,
    val poster: String,
    val overview: String,
    val release_date: String,
    val rating: Double,
    val isFavorite: Boolean
)