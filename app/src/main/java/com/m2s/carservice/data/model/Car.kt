package com.m2s.carservice.data.model

import android.os.Parcelable
import com.m2s.carservice.data.model.CarModel
import kotlinx.parcelize.Parcelize

@Parcelize
data class Car(
    val id: Int? = null,
    val brand: String = "",
    val models: List<CarModel> = listOf(),
    val imgUrl: String = ""
): Parcelable
