package com.brillante.core.data.local.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "tv_table")
@Parcelize
data class TvDiscoverEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: Int,

    @ColumnInfo(name = "poster")
    var poster: String,

    @ColumnInfo(name = "title")
    var title: String,

    @ColumnInfo(name = "rating")
    var rating: Double,

    @ColumnInfo(name = "trending")
    var isTrending: Boolean = false,

    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = false,

    ) : Parcelable