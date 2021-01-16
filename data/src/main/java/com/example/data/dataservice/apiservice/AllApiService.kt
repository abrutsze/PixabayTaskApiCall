package com.example.data.dataservice.apiservice

import com.example.entity.responcemodel.PixabayImageResponce
import retrofit2.Response
import retrofit2.http.*

interface AllApiService {

    @GET("api/")
    suspend fun getData(
        @Query("key") key: String,
        @Query("image_type") imageType: String,
    ): Response<PixabayImageResponce>

}
