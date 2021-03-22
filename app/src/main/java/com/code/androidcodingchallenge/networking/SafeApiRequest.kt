package com.code.androidcodingchallenge.networking

import android.util.Log
import com.code.androidcodingchallenge.utils.ApiException

import org.json.JSONException
import org.json.JSONObject
import retrofit2.Response


abstract class SafeApiRequest {
    suspend fun <T: Any> apiRequest(call: suspend () -> Response<T>) :T{
        val response = call.invoke()
        if (response.isSuccessful){

            return response.body()!!
        }
        else{
            val error = response.errorBody()?.string()
            val message = StringBuilder()
            error?.let {
                try {
                    message.append(JSONObject(it).getString("message"))
                }catch (e: JSONException){
                    Log.d("Json Exception" , "Json Exception")
                }
                message.append("\n")
            }
            message.append("Error Code: ${response.code()}")
            throw ApiException(message.toString())
        }
    }
}