package com.example.data.datastore

import com.example.entity.Result
import com.example.entity.responcemodel.PixabayImageResponce

interface GetDataRepository {
     suspend fun getDataListResponse(): Result<PixabayImageResponce>
}