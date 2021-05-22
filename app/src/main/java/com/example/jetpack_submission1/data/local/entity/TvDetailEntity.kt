package com.example.jetpack_submission1.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tv_detail_table")
data class TvDetailEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: Int = 0,

    @ColumnInfo(name = "title")
    var title: String? = null,

    @ColumnInfo(name = "numEpisode")
    var numberEpisdoe: Int = 0,

    @ColumnInfo(name = "numSeasons")
    var numberSeasons: Int = 0,

    @ColumnInfo(name = "poster")
    var poster: String? = null,

    @ColumnInfo(name = "overview")
    var overview: String? = null,

    @ColumnInfo(name = "rating")
    var rating: Double = 0.0
)