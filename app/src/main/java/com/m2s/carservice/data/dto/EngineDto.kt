package com.m2s.carservice.data.dto

import com.m2s.carservice.data.model.Engine
import com.google.gson.annotations.SerializedName

data class EngineDto(
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("name")
    val name: String = "",
    @SerializedName("car_model")
    val carModel: Int? = null
)

fun EngineDto.toEngine(): Engine {
    return Engine(
        id, name, carModel
    )
}