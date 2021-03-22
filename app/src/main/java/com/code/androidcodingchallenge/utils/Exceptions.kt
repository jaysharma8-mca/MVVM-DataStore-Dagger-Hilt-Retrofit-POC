package com.code.androidcodingchallenge.utils

import java.io.IOException


//handles exceptions
class ApiException(message: String) : IOException(message)
class NoInternetException(message: String): IOException(message)