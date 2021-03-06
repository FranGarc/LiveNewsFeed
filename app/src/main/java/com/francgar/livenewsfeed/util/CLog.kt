package com.francgar.livenewsfeed.util

import android.util.Log
import com.francgar.livenewsfeed.util.Constants.LOG_TAG

object CLog {
    fun v(message: String) {
        Log.v(LOG_TAG, message)
    }

    fun d(message: String) {
        Log.d(LOG_TAG, message)
    }

    fun i(message: String) {
        Log.i(LOG_TAG, message)
    }

    fun w(message: String) {
        Log.w(LOG_TAG, message)
    }

    fun wtf(message: String) {
        Log.wtf(LOG_TAG, message)
    }

    fun e(message: String, exception: Exception? = null) {
        if (exception != null) {
            Log.e(LOG_TAG, message, exception)
        } else {
            Log.e(LOG_TAG, message)
        }
    }

}