package com.m2s.carservice.data.api

import com.m2s.carservice.data.model.PushNotification
import com.m2s.carservice.util.Constants
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface NotificationApi {

    @Headers("Authorization: key=${Constants.SERVER_KEY}", "Content-Type:${Constants.CONTENT_TYPE}")
    @POST("fcm/send")
    suspend fun pushNotification(@Body notification: PushNotification): Response<ResponseBody>

}