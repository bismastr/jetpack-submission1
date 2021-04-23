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

class Detail(
    var id: Int = 0,
    var title: String? = null,
    var poster: String? = null,
    var overview: String? = null,
    var release_date: String? = null,
    var rating: Double = 0.0,
)

class DetailTrending(
    var id: Int = 0,
    var title: String? = null,
    var numberEpisdoe: Int = 0,
    var numberSeasons: Int = 0,
    var poster: String? = null,
    var overview: String? = null,
    var rating: Double = 0.0,
)