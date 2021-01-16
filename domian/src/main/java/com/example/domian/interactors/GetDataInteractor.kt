package com.example.domian.interactors

import com.example.entity.Result
import com.example.entity.localmodels.PixaBayListItem


interface GetDataInteractor {
  suspend  fun getDataResponse(): Result<List<PixaBayListItem>>
}