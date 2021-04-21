package com.example.jetpack_submission1.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class Movie (
    var id: Int = 0,
    var poster: String? = null,
    var title: String? = null,
    var rating: Double = 0.0
        ) : Parcelable