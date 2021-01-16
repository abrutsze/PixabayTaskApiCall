package com.example.data.repository

import com.example.data.dataservice.apiservice.AllApiService
import com.example.data.datastore.GetDataRepository
import com.example.data.utils.analyzeResponse
import com.example.data.utils.makeApiCall
import com.example.entity.Constants.Companion.API_KEY
import com.example.entity.Constants.Companion.IMAGE_TYPE
import retrofit2.Response
import com.example.entity.Result
import com.example.entity.responcemodel.PixabayImageResponce

class GetDataRepositoryImpl(private val allApiService: AllApiService) :
    GetDataRepository {

    override suspend fun getDataListResponse(): Result<PixabayImageResponce> =
        makeApiCall({
            getUserData(
                allApiService.getData(API_KEY,IMAGE_TYPE)
            )
        })

    private fun getUserData(popularMove: Response<PixabayImageResponce>): Result<PixabayImageResponce> =
        analyzeResponse(popularMove)
}