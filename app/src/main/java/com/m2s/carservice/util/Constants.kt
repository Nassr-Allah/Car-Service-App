package com.m2s.carservice.util

import com.m2s.carservice.BuildConfig

class Constants {

    companion object {
        const val FIREBASE_URL = "https://fcm.googleapis.com/"
        const val BASE_URL = BuildConfig.BASE_URL
        const val SERVER_KEY = BuildConfig.SERVER_KEY
        const val CONTENT_TYPE = "application/json"
    }

}