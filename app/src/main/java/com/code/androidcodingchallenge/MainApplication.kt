package com.code.androidcodingchallenge

import android.app.Activity
import android.app.Application
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.core.app.ActivityCompat.finishAffinity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ProcessLifecycleOwner
import com.code.androidcodingchallenge.utils.toast
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class MainApplication: Application(), LifecycleObserver {
    override fun onCreate() {
        super.onCreate()
        ProcessLifecycleOwner.get().lifecycle.addObserver(this)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onAppBackgrounded() {
        Handler(Looper.getMainLooper()).postDelayed({
            Log.d("Awww", "App in background")
            finishAffinity(Activity())
        }, 10000)


    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onAppForegrounded() {
        Log.d("Yeeey", "App in foreground")
    }
}