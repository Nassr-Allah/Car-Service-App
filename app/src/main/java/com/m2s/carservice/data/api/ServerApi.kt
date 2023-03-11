package com.m2s.carservice.data.api

import com.m2s.carservice.data.dto.*
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ServerApi {

    @GET("cars/")
    suspend fun getCarsList(): List<CarDto>

    @GET("reservations/")
    suspend fun getReservationsList(): List<ReservationDto>

    @POST("reservations/")
    suspend fun createReservation(@Body reservationDto: ReservationDto): Response<Void>

    @POST("cars/")
    suspend fun createCar(carDto: CarDto): Response<Void>

    @POST("cars/{name}/models/")
    suspend fun createModel(carModelDto: CarModelDto): Response<Void>

    @POST("services/")
    suspend fun createService(serviceDto: ServiceDto): Response<Void>

    @GET("services/")
    suspend fun getServicesList(): List<ServiceDto>


}