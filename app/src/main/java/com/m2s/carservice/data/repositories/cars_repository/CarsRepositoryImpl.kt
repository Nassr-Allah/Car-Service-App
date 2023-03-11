package com.m2s.carservice.data.repositories.cars_repository

import com.m2s.carservice.data.api.ServerApi
import com.m2s.carservice.data.dto.CarDto
import com.m2s.carservice.data.dto.CarModelDto
import com.m2s.carservice.data.dto.ServiceDto
import retrofit2.Response

class CarsRepositoryImpl(private val api: ServerApi) : CarsRepository {

    override suspend fun getCarsList(): List<CarDto> {
        return api.getCarsList()
    }

    override suspend fun createCar(carDto: CarDto): Response<Void> {
        return api.createCar(carDto)
    }

    override suspend fun createModel(carModelDto: CarModelDto): Response<Void> {
        return api.createModel(carModelDto)
    }

    override suspend fun createService(serviceDto: ServiceDto): Response<Void> {
        return api.createService(serviceDto)
    }

    override suspend fun getServicesList(): List<ServiceDto> {
        return api.getServicesList()
    }
}