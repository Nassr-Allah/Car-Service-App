package com.m2s.carservice.data.dto

import com.m2s.carservice.data.model.CarModel
import com.m2s.carservice.data.model.Engine
import com.google.gson.annotations.SerializedName

data class CarModelDto(
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("model_name")
    val name: String = "",
    @SerializedName("car")
    val car: Int = 0,
    @SerializedName("image_url")
    val imgUrl: String = "",
    @SerializedName("engine_set")
    val engines: List<EngineDto> = emptyList()
)

fun CarModelDto.toCarModel(): CarModel {
    val enginesList = mutableListOf<Engine>()
    for (engineDto in engines) {
        enginesList.add(engineDto.toEngine())
    }
    return CarModel(
        name = name,
        car = car,
        imgUrl = imgUrl,
        engines = enginesList
    )
}
