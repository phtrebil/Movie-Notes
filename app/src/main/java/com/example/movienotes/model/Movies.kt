package com.example.movienotes.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Movies(
     val id: Int,
     val title: String,
     val poster_path: String,
     val release_date: String,
     val overview: String,
     val vote_average: Float
): Parcelable {

}