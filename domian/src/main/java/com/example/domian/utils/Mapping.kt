package com.example.domian.utils


import com.example.entity.localmodels.*
import com.example.entity.responcemodel.*

fun Hit.fromHitToPixaBayModel(): PixaBayListItem {
    return PixaBayListItem(
        user = user?:"",
        previewURL = previewURL?:"",
        detailImageURL = webformatURL?:"",
        imageSize = imageSize?:0,
        type = type?:"",
        tags = tags?:"",
        views = views?:0,
        likes = likes?:0,
        comments = comments?:0,
        favorites = favorites?:0,
        downloads = downloads?:0
    )
}

fun UserDataDb.fromDBUserToUserModel(): UserData {
    return UserData(
        userName = userName ?: "",
        age = age ?: 0
    )
}



