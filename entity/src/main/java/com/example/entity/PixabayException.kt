package com.example.entity

data class PixabayException<ErrorBody>(val errorCode: Int,  val errorMessage:String?=null, val errorBody: ErrorBody? = null,)