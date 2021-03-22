package com.code.androidcodingchallenge

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.lifecycle.asLiveData
import com.code.androidcodingchallenge.R
import com.code.androidcodingchallenge.homescreen.HomeActivity
import com.code.androidcodingchallenge.userpreferences.UserPreferences
import com.code.androidcodingchallenge.utils.startNewActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

    }
}