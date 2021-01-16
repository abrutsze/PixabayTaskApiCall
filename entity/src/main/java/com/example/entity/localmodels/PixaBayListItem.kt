package com.example.entity.localmodels

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

data class PixaBayListItem(
    val user: String,
    val previewURL: String,
    val detailImageURL: String,
    val imageSize: Int,
    val type: String,
    val tags: String,
    val views: Int,
    val likes: Int,
    val comments: Int,
    val favorites: Int,
    val downloads: Int
):Serializable
