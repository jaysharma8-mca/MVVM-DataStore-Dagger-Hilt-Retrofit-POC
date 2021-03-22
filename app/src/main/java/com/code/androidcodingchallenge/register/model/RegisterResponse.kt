package com.code.androidcodingchallenge.register.model

data class RegisterResponse(
    val code: String,
    val message: String,
    val user: User
)