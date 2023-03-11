package com.m2s.carservice.data.dto

import com.google.gson.annotations.SerializedName

data class ReservationDto(
    @SerializedName("client_full_name")
    val clientName: String = "",
    @SerializedName("client_phone")
    val clientPhone: String = "",
    @SerializedName("reservation_date")
    val date: String = "",
    @SerializedName("reservation_services")
    val services: String = "",
    @SerializedName("car_year")
    val carYear: String = "",
    @SerializedName("car_engine")
    val carEngine: String = "",
    @SerializedName("car_brand")
    val carBrand: String = "",
    @SerializedName("car_model")
    val carModel: String = "",
    @SerializedName("engine_type")
    val engineType: String = "",
    @SerializedName("reservation_time")
    val time: String,
)
