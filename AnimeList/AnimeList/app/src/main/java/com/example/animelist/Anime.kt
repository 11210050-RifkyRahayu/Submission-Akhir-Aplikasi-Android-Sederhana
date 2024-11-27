package com.example.animelist

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Anime(
    val name: String,
    val description: String,
    val photo: Int
) :Parcelable
