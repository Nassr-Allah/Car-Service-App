package com.m2s.carservice.data.repositories.reservations_repository

import com.m2s.carservice.data.dto.ReservationDto
import retrofit2.Response

interface ReservationsRepository {

    suspend fun getReservationsList(): List<ReservationDto>

    suspend fun createReservation(reservationDto: ReservationDto): Response<Void>

}