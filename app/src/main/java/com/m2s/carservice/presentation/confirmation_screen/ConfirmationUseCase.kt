package com.m2s.carservice.presentation.confirmation_screen

import android.util.Log
import com.m2s.carservice.data.dto.ReservationDto
import com.m2s.carservice.data.repositories.reservations_repository.ReservationsRepository
import com.m2s.carservice.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import retrofit2.Response
import javax.inject.Inject

class ConfirmationUseCase @Inject constructor(
    private val repository: ReservationsRepository
) {

    operator fun invoke(reservationDto: ReservationDto): Flow<Resource<Response<Void>>> = flow {
        try {
            emit(Resource.Loading<Response<Void>>())
            val response = repository.createReservation(reservationDto)
            Log.d("ConfUseCase", response.toString())
            emit(Resource.Success(response))
        } catch (e: HttpException) {
            emit(Resource.Error<Response<Void>>(e.localizedMessage ?: "Unexpected Server Error"))
        } catch (e: IOException) {
            emit(Resource.Error<Response<Void>>( e.localizedMessage ?: "Unexpected Error"))
        }
    }

}