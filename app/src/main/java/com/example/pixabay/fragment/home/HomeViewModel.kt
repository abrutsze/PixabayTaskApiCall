package com.example.pixabay.fragment.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domian.interactors.GetDataInteractor

import com.example.entity.Result
import com.example.entity.localmodels.PixaBayListItem
import com.example.pixabay.base.viewmodel.BaseViewModel
import com.example.pixabay.base.viewmodel.SingleLiveEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel(
    private val getDataInteractor: GetDataInteractor
) : BaseViewModel() {

    private val _getUserDataList by lazy { MutableLiveData<List<PixaBayListItem>>() }
    val getDataModelImagesItemList: LiveData<List<PixaBayListItem>> = _getUserDataList
    private val _loadingData by lazy { MutableLiveData<Boolean>() }
    val loadingData: LiveData<Boolean> = _loadingData
    private val _errorNullData by lazy { MutableLiveData<String>() }
    val errorNullData get() = _errorNullData

    init {
        getDataList()
    }

    private fun getDataList() {
        _loadingData.value = true
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = getDataInteractor.getDataResponse()) {
                is Result.Success -> withContext(Dispatchers.Main) {
                    _getUserDataList.value = result.data
                    _loadingData.value = false
                }
                is Result.Error -> withContext(Dispatchers.Main) {
                    _errorNullData.value = result.errors.errorMessage
                    _loadingData.value = false
                }
            }
        }
    }
}

