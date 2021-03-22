package com.code.androidcodingchallenge.login.model

data class LoginResponse(
    val code: String,
    val message: String,
    val user: User
)