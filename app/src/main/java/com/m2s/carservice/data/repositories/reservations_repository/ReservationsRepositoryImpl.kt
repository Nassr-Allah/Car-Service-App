package com.m2s.carservice.data.repositories.reservations_repository

import com.m2s.carservice.data.api.ServerApi
import com.m2s.carservice.data.dto.ReservationDto
import retrofit2.Response

class ReservationsRepositoryImpl(private val api: ServerApi) : ReservationsRepository {

    override suspend fun getReservationsList(): List<ReservationDto> {
        return api.getReservationsList()
    }

    override suspend fun createReservation(reservationDto: ReservationDto): Response<Void> {
        return api.createReservation(reservationDto)
    }
}