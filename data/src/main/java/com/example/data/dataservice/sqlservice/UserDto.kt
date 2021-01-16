package com.example.data.dataservice.sqlservice

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.entity.responcemodel.UserDataDb

@Dao
interface UserDto {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun registerUser(user: UserDataDb)

    @Query("SELECT * FROM User where userName = :username AND password=:password")
    fun loginUser(username: String, password: String): UserDataDb?

}