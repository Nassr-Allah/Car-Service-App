package com.m2s.carservice.presentation.cars_screen

import android.util.Log
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.m2s.carservice.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CarsViewModel @Inject constructor(
    private val useCase: CarsListUseCase
) : ViewModel() {

    private val _state = mutableStateOf(CarsState())
    val state: State<CarsState> = _state

    init {
        getCarsList()
    }

    private fun getCarsList() {
        useCase().onEach { result ->
            when(result) {
                is Resource.Success -> {
                    _state.value = CarsState(isLoading = false, cars = result.data ?: emptyList())
                }
                is Resource.Loading -> {
                    _state.value = CarsState(isLoading = true)
                }
                is Resource.Error -> {
                    Log.d("CarsViewModel", "Http Error Received")
                    _state.value = CarsState(isLoading = false, error = result.message ?: "Unexpected Error")
                }
            }
        }.launchIn(viewModelScope)
    }

}

