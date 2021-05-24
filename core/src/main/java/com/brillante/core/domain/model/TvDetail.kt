package com.brillante.core.domain.model

data class TvDetail(

    var id: Int,
    var title: String,
    var numberEpisode: Int,
    var numberSeasons: Int,
    var poster: String,
    var overview: String,
    var rating: Double,
    var isFavorite: Boolean
)