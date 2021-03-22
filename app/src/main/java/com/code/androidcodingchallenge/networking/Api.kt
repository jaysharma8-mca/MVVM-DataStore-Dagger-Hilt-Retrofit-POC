package com.code.androidcodingchallenge.networking


import com.code.androidcodingchallenge.login.model.LoginResponse
import com.code.androidcodingchallenge.register.model.RegisterResponse
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface Api {

    @FormUrlEncoded
    @POST("register.php")
    suspend fun registration(
        @Field("full_name") full_name : String,
        @Field("user_name") user_name : String,
        @Field("password") password : String,
    ): Response<RegisterResponse>

    @FormUrlEncoded
    @POST("login.php")
    suspend fun login(
        @Field("user_name") user_name : String,
        @Field("passwd") passwd : String,
    ): Response<LoginResponse>
}