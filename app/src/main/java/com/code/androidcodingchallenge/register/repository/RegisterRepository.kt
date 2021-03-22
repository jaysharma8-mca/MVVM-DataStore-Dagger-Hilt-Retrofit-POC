package com.code.androidcodingchallenge.register.repository


import com.code.androidcodingchallenge.networking.Api
import com.code.androidcodingchallenge.networking.SafeApiRequest
import com.code.androidcodingchallenge.register.model.RegisterResponse
import com.code.androidcodingchallenge.userpreferences.UserPreferences
import javax.inject.Inject


class RegisterRepository @Inject constructor(
    private val api: Api,
    private val preferences: UserPreferences
) : SafeApiRequest(){
    suspend fun registration(
        full_name : String,
        user_name: String,
        password: String
    ): RegisterResponse {
        return apiRequest { api.registration(full_name, user_name, password) }
    }

    suspend fun saveData(fullName: String, userName: String, password: String){
        preferences.saveData(fullName, userName, password)
    }
}