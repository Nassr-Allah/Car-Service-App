package com.m2s.carservice.data.model

import android.os.Parcelable
import com.m2s.carservice.data.dto.EngineDto
import kotlinx.parcelize.Parcelize

@Parcelize
data class Engine(
    val id: Int? = null,
    val name: String = "",
    val carModel: Int? = null,
): Parcelable

fun Engine.toEngineDto(): EngineDto {
    return EngineDto(
        id, name, carModel
    )
}
