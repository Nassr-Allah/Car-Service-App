package com.m2s.carservice.presentation.service_screen

import com.m2s.carservice.data.repositories.cars_repository.CarsRepository
import com.m2s.carservice.util.Resource
import com.m2s.carservice.data.dto.toService
import com.m2s.carservice.data.model.Service
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class ServicesUseCase @Inject constructor(
    private val repository: CarsRepository
) {

    operator fun invoke(): Flow<Resource<List<Service>>> = flow {
        try {
            emit(Resource.Loading<List<Service>>())
            val services = repository.getServicesList().map { it.toService() }
            emit(Resource.Success(services))
        } catch (e: HttpException) {
            emit(Resource.Error<List<Service>>(e.localizedMessage ?: "Unexpected Server Error"))
        } catch (e: IOException) {
            emit(Resource.Error<List<Service>>(e.localizedMessage ?: "Unexpected Error"))
        }
    }

}