package com.brillante.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieDiscover(
    val id: Int,
    val poster: String?,
    val title: String?,
    val rating: Double,
    val isTrending: Boolean = false
) : Parcelable