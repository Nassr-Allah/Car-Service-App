package com.m2s.carservice.data.dto

import android.os.Parcelable
import com.m2s.carservice.data.model.MiniService
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class MiniServiceDto(
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("name")
    val name: String = ""
): Parcelable

fun MiniServiceDto.toMiniService(): MiniService {
    return MiniService(id, name)
}
