package com.m2s.carservice.data.dto

import com.m2s.carservice.data.model.Car
import com.m2s.carservice.data.model.CarModel
import com.google.gson.annotations.SerializedName

data class CarDto(
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("car_brand")
    val carBrand: String = "",
    @SerializedName("carmodel_set")
    val models: List<CarModelDto> = listOf(),
    @SerializedName("image_url")
    val imgUrl: String = ""
)

fun CarDto.toCar(): Car {
    val modelsList = mutableListOf<CarModel>()
    models.forEach { carModelDto ->
        modelsList.add(carModelDto.toCarModel())
    }
    return Car(
        id = id,
        brand = carBrand,
        models = modelsList,
        imgUrl = imgUrl
    )
}
