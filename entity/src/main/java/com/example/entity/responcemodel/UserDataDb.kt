package com.example.entity.responcemodel

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "User")
data class UserDataDb(
    @PrimaryKey
    val id: String = UUID.randomUUID().toString(),
    @ColumnInfo(name = "userName")
    var userName: String? = null,
    @ColumnInfo(name = "password")
    var password: String? = null,
    @ColumnInfo(name = "age")
    var age: Int? = null
)
