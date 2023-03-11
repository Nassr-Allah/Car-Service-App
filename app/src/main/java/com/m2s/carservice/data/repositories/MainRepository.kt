package com.m2s.carservice.data.repositories

import com.m2s.carservice.data.repositories.cars_repository.CarsRepository
import com.m2s.carservice.data.repositories.reservations_repository.ReservationsRepository

data class MainRepository(
    val carsRepository: CarsRepository,
    val reservationsRepository: ReservationsRepository
)
