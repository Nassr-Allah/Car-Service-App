package com.m2s.carservice.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MiniService(
    val id: Int? = null,
    val name: String = "",
    var isChecked: Boolean = false
): Parcelable
