package com.example.jetpack_submission1.domain.model

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

data class TvDetail(
    var id: Int = 0,
    var title: String? = null,
    var numberEpisode: Int = 0,
    var numberSeasons: Int = 0,
    var poster: String? = null,
    var overview: String? = null,
    var rating: Double = 0.0
)