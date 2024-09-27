package com.teka.googlemapsproject.core

import android.util.Log
import com.teka.googlemapsproject.core.Constants.TAG

class Utils {
    companion object {
        fun print(e: Exception?) = e?.apply {
            Log.e(TAG, stackTraceToString())
        }
    }
}