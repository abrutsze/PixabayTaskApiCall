package com.example.data.datastore

import com.example.entity.responcemodel.UserDataDb

interface LoginRegRepository {
    suspend fun loginUser(username: String, password: String):UserDataDb?
    suspend fun regUser(user:UserDataDb)
}