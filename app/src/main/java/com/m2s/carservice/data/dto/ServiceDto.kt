package com.m2s.carservice.data.dto

import com.m2s.carservice.data.model.MiniService
import com.m2s.carservice.data.model.Service
import com.google.gson.annotations.SerializedName

data class ServiceDto(
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("service_name")
    val name: String = "",
    @SerializedName("miniservice_set")
    val miniServices: List<MiniServiceDto> = emptyList()
)

fun ServiceDto.toService(): Service {
    val miniServicesList = mutableListOf<MiniService>()
    for (miniServiceDto in miniServices) {
        miniServicesList.add(miniServiceDto.toMiniService())
    }
    return Service(
        id,
        name,
        miniServicesList
    )
}
