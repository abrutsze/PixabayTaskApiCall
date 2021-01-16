package com.example.pixabay.fragment.registration

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.domian.interactors.LoginRegInteractor
import com.example.entity.Constants
import com.example.entity.Result
import com.example.entity.localmodels.UserData
import com.example.pixabay.base.viewmodel.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RegistrationViewModel(private val loginRegInteractor: LoginRegInteractor) : BaseViewModel() {

    private val _loginIsIncorrect = MutableLiveData<Unit>()
    val loginIsIncorrect: LiveData<Unit> = _loginIsIncorrect
    private val _passwordIsIncorrect = MutableLiveData<Unit>()
    val passwordIsIncorrect: LiveData<Unit> = _passwordIsIncorrect
    private val _ageIsIncorrect = MutableLiveData<Unit>()
    val ageIsIncorrect: LiveData<Unit> = _ageIsIncorrect
    private val _registrationResult = MutableLiveData<UserData>()
    val registrationResult: LiveData<UserData> = _registrationResult
    private val _registrationResultError = MutableLiveData<Unit>()
    val registrationResultError: LiveData<Unit> = _registrationResultError

    fun registration(username: String, password: String, age: String) {
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = loginRegInteractor.registrationUser(username, password, age)) {
                is Result.Success -> withContext(Dispatchers.Main) {
                    _registrationResult.value = result.data
                }
                is Result.Error -> {
                    when (result.errors.errorCode) {
                        Constants.INVALID_LOGIN -> withContext(Dispatchers.Main) {
                            _loginIsIncorrect.value = Unit
                        }
                        Constants.INVALID_PASSWORD -> withContext(Dispatchers.Main) {
                            _passwordIsIncorrect.value = Unit
                        }
                        Constants.INVALID_AGE -> withContext(Dispatchers.Main) {
                            _ageIsIncorrect.value = Unit
                        }
                        Constants.INVALID_USER -> withContext(Dispatchers.Main) {
                            _registrationResultError.value = Unit
                        }

                    }
                }
            }
        }
    }


}