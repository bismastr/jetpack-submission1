package com.example.jetpack_submission1.data.local.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieDiscoverEntity(
    var id: Int = 0,
    var poster: String? = null,
    var title: String? = null,
    var rating: Double = 0.0
) : Parcelable