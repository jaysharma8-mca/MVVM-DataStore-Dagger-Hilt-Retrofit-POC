package com.code.androidcodingchallenge.register.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.code.androidcodingchallenge.register.repository.RegisterRepository
import com.code.androidcodingchallenge.utils.DispatcherProvider
import kotlinx.coroutines.withContext

class RegisterViewModel @ViewModelInject constructor(
    private val repository: RegisterRepository,
    private val dispatchers: DispatcherProvider
): ViewModel() {
    suspend fun registration(
        full_name : String,
        user_name: String,
        password: String,
    ) = withContext(dispatchers.io){
        repository.registration(full_name, user_name, password)
    }

    suspend fun saveData(fullName: String, userName: String, password: String) {
        repository.saveData(fullName, userName, password)
    }

}