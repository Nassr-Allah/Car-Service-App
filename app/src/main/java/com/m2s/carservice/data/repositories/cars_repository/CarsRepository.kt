package com.m2s.carservice.data.repositories.cars_repository

import com.m2s.carservice.data.dto.CarDto
import com.m2s.carservice.data.dto.CarModelDto
import com.m2s.carservice.data.dto.ServiceDto
import retrofit2.Response

interface CarsRepository {

    suspend fun getCarsList(): List<CarDto>

    suspend fun createCar(carDto: CarDto): Response<Void>

    suspend fun createModel(carModelDto: CarModelDto): Response<Void>

    suspend fun createService(serviceDto: ServiceDto): Response<Void>

    suspend fun getServicesList(): List<ServiceDto>

}