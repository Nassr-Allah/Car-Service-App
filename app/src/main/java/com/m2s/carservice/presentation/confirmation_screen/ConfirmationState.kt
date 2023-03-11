package com.m2s.carservice.presentation.confirmation_screen

import retrofit2.Response

data class ConfirmationState(
    val isLoading: Boolean = false,
    val data: Response<Void>? = null,
    val error: String = ""
)
