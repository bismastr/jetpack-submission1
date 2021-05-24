package com.brillante.core.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tv_detail_table")
data class TvDetailEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: Int,

    @ColumnInfo(name = "title")
    var title: String,

    @ColumnInfo(name = "numEpisode")
    var numberEpisdoe: Int,

    @ColumnInfo(name = "numSeasons")
    var numberSeasons: Int,

    @ColumnInfo(name = "poster")
    var poster: String,

    @ColumnInfo(name = "overview")
    var overview: String,

    @ColumnInfo(name = "rating")
    var rating: Double,

    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = false
)