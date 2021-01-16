package com.example.domian.interactors

import com.example.entity.Result
import com.example.entity.localmodels.UserData

interface LoginRegInteractor {
    suspend fun loginUser(username: String, password: String): Result<UserData>
    suspend fun registrationUser(username: String, password: String, age: String): Result<UserData>
}