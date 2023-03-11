package com.m2s.carservice.data.model

import android.os.Parcelable
import com.m2s.carservice.data.dto.ReservationDto
import kotlinx.parcelize.Parcelize

@Parcelize
data class Reservation(
    val id: Int? = null,
    var clientName: String = "",
    var clientPhone: String = "",
    var date: String = "",
    val services: List<MiniService> = listOf(),
    val carYear: String = "",
    val carEngine: String = "",
    val carBrand: String = "",
    val carModel: String = "",
    val engineType: String = "",
    var isChecked: Boolean = false,
    var time: String = "",
) : Parcelable


fun Reservation.toReservationDto(): ReservationDto {
    var servicesStr = ""
    for (s in services) {
        servicesStr += "- ${s.name}. \n"
    }
    return ReservationDto(
        clientName = clientName,
        clientPhone = clientPhone,
        date = date,
        services = servicesStr,
        carYear = carYear,
        carEngine = carEngine,
        carBrand = carBrand,
        carModel = carModel,
        engineType = engineType,
        time = time,
    )
}
