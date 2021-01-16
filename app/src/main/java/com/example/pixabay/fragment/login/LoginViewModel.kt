package com.example.pixabay.fragment.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.domian.interactors.LoginRegInteractor
import com.example.entity.Constants
import com.example.entity.Result
import com.example.entity.localmodels.UserData
import com.example.pixabay.base.viewmodel.BaseViewModel
import com.example.pixabay.base.viewmodel.SingleLiveEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginViewModel(private val loginRegInteractor: LoginRegInteractor) : BaseViewModel() {
    private val _loginIsIncorrect = MutableLiveData<Unit>()
    val loginIsIncorrect: LiveData<Unit> = _loginIsIncorrect
    private val _passwordIsIncorrect = MutableLiveData<Unit>()
    val passwordIsIncorrect: LiveData<Unit> = _passwordIsIncorrect
    private val _loginResult = SingleLiveEvent<UserData>()
    val loginResult: LiveData<UserData> = _loginResult
    private val _loginResultError = MutableLiveData<Unit>()
    val loginResultError: LiveData<Unit> = _loginResultError


    fun login(username: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = loginRegInteractor.loginUser(username, password)) {
                is Result.Success -> withContext(Dispatchers.Main) {
                    _loginResult.value = result.data
                }
                is Result.Error -> {
                    when (result.errors.errorCode) {
                        Constants.INVALID_LOGIN -> withContext(Dispatchers.Main) {
                            _loginIsIncorrect.value = Unit
                        }
                        Constants.INVALID_PASSWORD -> withContext(Dispatchers.Main) {
                            _passwordIsIncorrect.value = Unit
                        }
                        Constants.INVALID_USER -> withContext(Dispatchers.Main) {
                            _loginResultError.value = Unit
                        }
                    }
                }
            }
        }
    }

}