package com.m2s.carservice.presentation.confirmation_screen


import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.m2s.carservice.data.api.NotificationApi
import com.m2s.carservice.data.repositories.reservations_repository.ReservationsRepository
import com.m2s.carservice.data.dto.ReservationDto
import com.m2s.carservice.data.model.NotificationData
import com.m2s.carservice.data.model.PushNotification
import com.m2s.carservice.util.Resource
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import javax.inject.Inject

@HiltViewModel
class ConfirmationViewModel @Inject constructor(
    private val useCase: ConfirmationUseCase,
    private val notificationApi: NotificationApi
) : ViewModel() {

    private val _state = mutableStateOf(ConfirmationState())
    val state: State<ConfirmationState> = _state

    fun createReservation(reservationDto: ReservationDto) {
        useCase(reservationDto).onEach { result ->
            when(result) {
                is Resource.Success -> {
                    if (result.data != null) {
                        if (result.data.isSuccessful) {
                            _state.value = ConfirmationState(isLoading = false, data = result.data)
                            val notification = PushNotification(
                                NotificationData("M2S", reservationDto.clientName),
                                "/topics/notifications"
                            )
                            sendNotification(notification)
                        } else {
                            _state.value = ConfirmationState(isLoading = false, error = result.message ?: "Unexpected Error")
                        }
                    } else {
                        _state.value = ConfirmationState(isLoading = false, error = "Unexpected Error")
                    }
                }
                is Resource.Loading -> {
                    _state.value = ConfirmationState(isLoading = true)
                }
                is Resource.Error -> {
                    _state.value = ConfirmationState(isLoading = false, error = result.message ?: "Unexpected Error")
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun sendNotification(notification: PushNotification) = CoroutineScope(Dispatchers.IO).launch {
        try {
            val response = notificationApi.pushNotification(notification)
            if (response.isSuccessful) {
                Log.d("ConfirmationViewModel", "Response: $response")
            } else {
                Log.d("ConfirmationViewModel", response.errorBody().toString())
            }
        } catch (e: Exception) {
            Log.d("ConfirmationViewModel", e.localizedMessage ?: "Unexpected Error")
        }
    }

}