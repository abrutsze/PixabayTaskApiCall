package com.example.domian.usecase

import com.example.data.datastore.LoginRegRepository
import com.example.domian.interactors.LoginRegInteractor
import com.example.domian.utils.fromDBUserToUserModel
import com.example.entity.Constants.Companion.INVALID_AGE
import com.example.entity.Constants.Companion.INVALID_LOGIN
import com.example.entity.Constants.Companion.INVALID_PASSWORD
import com.example.entity.Constants.Companion.INVALID_USER
import com.example.entity.PixabayException
import com.example.entity.Result
import com.example.entity.localmodels.UserData
import com.example.entity.responcemodel.UserDataDb
import java.util.regex.Matcher
import java.util.regex.Pattern

class LoginRegUseCase(private val loginRegRepository: LoginRegRepository) : LoginRegInteractor {

    override suspend fun loginUser(username: String, password: String): Result<UserData> {
        return when {
            !isEmailValid(username) -> {
                Result.Error(PixabayException(INVALID_LOGIN))
            }
            !isPasswordValid(password) -> {
                Result.Error(PixabayException(INVALID_PASSWORD))
            }
            else -> {
                val data = loginRegRepository.loginUser(username, password).apply { }
                data?.let { userData ->
                    userData.userName?.let {
                        Result.Success(userData.fromDBUserToUserModel())
                    } ?: Result.Error(PixabayException(INVALID_USER))
                } ?: Result.Error(PixabayException(INVALID_USER))

            }
        }
    }

    override suspend fun registrationUser(
        username: String,
        password: String,
        age: String
    ): Result<UserData> {
        return when {
            !isEmailValid(username) -> {
                Result.Error(PixabayException(INVALID_LOGIN))
            }
            !isPasswordValid(password) -> {
                Result.Error(PixabayException(INVALID_PASSWORD))
            }
            !isAgeValid(age) -> {
                Result.Error(PixabayException(INVALID_AGE))
            }
            else -> {
                loginRegRepository.regUser(
                    UserDataDb(
                        userName = username,
                        password = password,
                        age = age.toInt()
                    )
                )
                Result.Success(UserData(username, age.toInt()))
            }
        }
    }

    private fun isEmailValid(email: String): Boolean {
        val expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$"
        val pattern: Pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE)
        val matcher: Matcher = pattern.matcher(email)
        return matcher.matches()
    }

    private fun isPasswordValid(userName: String): Boolean {
        return userName.length in 6..12
    }

    private fun isAgeValid(age: String): Boolean {
        return if (age.isNotEmpty()) {
            age.toInt() in 18..99
        } else false
    }
}