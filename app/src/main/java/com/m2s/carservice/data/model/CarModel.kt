package com.m2s.carservice.data.model

import android.os.Parcelable
import com.m2s.carservice.data.dto.CarModelDto
import com.m2s.carservice.data.dto.EngineDto
import kotlinx.parcelize.Parcelize

@Parcelize
data class CarModel(
    val name: String = "",
    val car: Int = 0,
    val imgUrl: String = "",
    val engines: List<Engine> = emptyList()
) : Parcelable

fun CarModel.toCarModelDto(): CarModelDto {
    val enginesList = mutableListOf<EngineDto>()
    for (engine in engines) {
        enginesList.add(engine.toEngineDto())
    }
    return CarModelDto(
        name = name,
        car = car,
        imgUrl = imgUrl,
        engines = enginesList
    )
}
