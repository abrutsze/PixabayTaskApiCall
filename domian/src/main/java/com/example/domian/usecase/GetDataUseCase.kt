package com.example.domian.usecase

import com.example.data.datastore.GetDataRepository
import com.example.domian.interactors.GetDataInteractor
import com.example.domian.utils.fromHitToPixaBayModel
import com.example.entity.PixabayException
import com.example.entity.Constants.Companion.ERROR_NULL_DATA
import com.example.entity.Result
import com.example.entity.localmodels.PixaBayListItem

class GetDataUseCase(
    private val getDataRepository: GetDataRepository
) : GetDataInteractor {

    override suspend fun getDataResponse(): Result<List<PixaBayListItem>> {

        return when (val apiData = getDataRepository.getDataListResponse()) {
            is Result.Success -> {
                apiData.data?.let { it ->

                    Result.Success(it.hits.map { it.fromHitToPixaBayModel() })

                } ?: Result.Error(
                    PixabayException(
                        ERROR_NULL_DATA
                    )
                )

            }
            is Result.Error -> {
                Result.Error(
                    PixabayException(
                        ERROR_NULL_DATA,
                        apiData.errors.errorMessage
                    )
                )

            }
        }

    }

}