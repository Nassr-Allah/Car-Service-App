package com.m2s.carservice.presentation.service_screen

import com.m2s.carservice.data.model.Service

data class ServiceState(
    val isLoading: Boolean = false,
    val data: List<Service> = emptyList(),
    val error: String = ""
)
