package com.example.data.dataservice.sqlservice

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.entity.responcemodel.UserDataDb

@Database(
    entities = [UserDataDb::class],
    version = 1,
    exportSchema = false
)

abstract class AppDatabase: RoomDatabase() {
    abstract fun pokemonListDao():UserDto
}