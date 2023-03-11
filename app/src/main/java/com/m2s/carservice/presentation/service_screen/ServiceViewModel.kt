package com.m2s.carservice.presentation.service_screen

import android.app.Application
import android.widget.Toast
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.m2s.carservice.util.Resource
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ServiceViewModel @Inject constructor(
    private val useCase: ServicesUseCase,
) : ViewModel() {

    private val _state = mutableStateOf(ServiceState())
    val state: State<ServiceState> = _state

    init {
        getServicesList()
    }

    private fun getServicesList() {
        useCase().onEach { result ->
            when(result) {
                is Resource.Loading -> {
                    _state.value = ServiceState(isLoading = false)
                }
                is Resource.Success -> {
                    _state.value = ServiceState(isLoading = false, data = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _state.value = ServiceState(isLoading = false, error = result.message ?: "Unexpected Error")
                }
            }
        }.launchIn(viewModelScope)
    }

}