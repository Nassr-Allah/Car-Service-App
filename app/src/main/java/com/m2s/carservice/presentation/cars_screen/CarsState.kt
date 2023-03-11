package com.m2s.carservice.presentation.cars_screen

import com.m2s.carservice.data.model.Car

data class CarsState(
    val isLoading: Boolean = false,
    val cars: List<Car> = emptyList(),
    val error: String = ""
)
