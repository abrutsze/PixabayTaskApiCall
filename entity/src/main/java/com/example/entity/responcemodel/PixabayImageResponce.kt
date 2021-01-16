package com.example.entity.responcemodel


import com.squareup.moshi.Json

data class PixabayImageResponce(
    @Json(name = "total")
    val total: Int,
    @Json(name = "totalHits")
    val totalHits: Int,
    @Json(name = "hits")
    val hits: List<Hit>
)