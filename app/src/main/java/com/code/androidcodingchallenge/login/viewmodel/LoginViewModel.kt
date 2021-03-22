@file:Suppress("DEPRECATION")

package com.code.androidcodingchallenge.login.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.code.androidcodingchallenge.login.repository.LoginRepository
import com.code.androidcodingchallenge.utils.DispatcherProvider
import kotlinx.coroutines.withContext

class LoginViewModel @ViewModelInject constructor(
    private val repository: LoginRepository,
    private val dispatchers: DispatcherProvider
): ViewModel() {
    suspend fun login(
        user_name: String,
        password: String,
    ) = withContext(dispatchers.io){
        repository.login(user_name, password)
    }
}