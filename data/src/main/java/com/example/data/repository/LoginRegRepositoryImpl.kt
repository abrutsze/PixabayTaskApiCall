package com.example.data.repository

import com.example.data.dataservice.sqlservice.UserDto
import com.example.data.datastore.LoginRegRepository
import com.example.entity.responcemodel.UserDataDb

class LoginRegRepositoryImpl(private val userDao: UserDto) : LoginRegRepository {
    override suspend fun loginUser(username: String, password: String) :UserDataDb?=userDao.loginUser(username, password)


    override suspend fun regUser(user: UserDataDb) {
        userDao.registerUser(user)
    }
}