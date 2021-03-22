package com.code.androidcodingchallenge.login.repository

import com.code.androidcodingchallenge.login.model.LoginResponse
import com.code.androidcodingchallenge.networking.Api
import com.code.androidcodingchallenge.networking.SafeApiRequest
import javax.inject.Inject

class LoginRepository @Inject constructor(
    private val loginApi: Api,
) : SafeApiRequest(){
    suspend fun login(
        user_name: String,
        password: String
    ): LoginResponse{
        return apiRequest { loginApi.login(user_name, password) }
    }
}