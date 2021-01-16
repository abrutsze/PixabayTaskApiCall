package com.example.entity.responcemodel
import com.squareup.moshi.Json

data class Hit(
    @field:Json(name = "id")
    val id: Int?=null,
    @field:Json(name = "pageURL")
    val pageURL: String?=null,
    @field:Json(name = "type")
    val type: String?=null,
    @field:Json(name = "tags")
    val tags: String?=null,
    @field:Json(name = "previewURL")
    val previewURL: String?=null,
    @field:Json(name = "previewWidth")
    val previewWidth: Int?=null,
    @field:Json(name = "previewHeight")
    val previewHeight: Int?=null,
    @field:Json(name = "webformatURL")
    val webformatURL: String?=null,
    @field:Json(name = "webformatWidth")
    val webformatWidth: Int?=null,
    @field:Json(name = "webformatHeight")
    val webformatHeight: Int?=null,
    @field:Json(name = "largeImageURL")
    val largeImageURL: String?=null,
    @field:Json(name = "imageWidth")
    val imageWidth: Int?=null,
    @field:Json(name = "imageHeight")
    val imageHeight: Int?=null,
    @field:Json(name = "imageSize")
    val imageSize: Int?=null,
    @field:Json(name = "views")
    val views: Int?=null,
    @field:Json(name = "downloads")
    val downloads: Int?=null,
    @field:Json(name = "favorites")
    val favorites: Int?=null,
    @field:Json(name = "likes")
    val likes: Int?=null,
    @field:Json(name = "comments")
    val comments: Int?=null,
    @field:Json(name = "user_id")
    val userId: Int?=null,
    @field:Json(name = "user")
    val user: String?=null,
    @field:Json(name = "userImageURL")
    val userImageURL: String?=null
)