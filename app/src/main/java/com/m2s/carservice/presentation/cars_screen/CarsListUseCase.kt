package com.m2s.carservice.presentation.cars_screen

import android.util.Log
import com.m2s.carservice.data.dto.toCar
import com.m2s.carservice.data.repositories.cars_repository.CarsRepository
import com.m2s.carservice.util.Resource
import com.m2s.carservice.data.model.Car
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class CarsListUseCase @Inject constructor(
    private val repository: CarsRepository
) {

    operator fun invoke(): Flow<Resource<List<Car>>> = flow {
        try {
            Log.d("CarsUseCase", "started the call ...")
            emit(Resource.Loading<List<Car>>())
            val cars = repository.getCarsList()
                .map { it.toCar() }
                .sortedBy { it.id }
            emit(Resource.Success(cars))
        } catch (e: HttpException) {
            Log.d("CarsUseCase", "http error")
            emit(Resource.Error<List<Car>>(e.localizedMessage ?: "Unknown Server Error"))
        } catch (e: IOException) {
            Log.d("CarsUseCase", "IO Error")
            emit(Resource.Error<List<Car>>(e.localizedMessage ?: "Unexpected Error"))
        }
    }

}