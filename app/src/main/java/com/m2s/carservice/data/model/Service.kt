package com.m2s.carservice.data.model

import android.os.Parcelable
import com.m2s.carservice.data.dto.ServiceDto
import kotlinx.parcelize.Parcelize

@Parcelize
data class Service(
    val id: Int? = null,
    val name: String = "",
    val miniServices: List<MiniService> = emptyList(),
) : Parcelable

