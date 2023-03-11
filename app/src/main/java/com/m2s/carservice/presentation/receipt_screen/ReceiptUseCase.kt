package com.m2s.carservice.presentation.receipt_screen

import com.m2s.carservice.data.repositories.cars_repository.CarsRepository
import com.m2s.carservice.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class ReceiptUseCase @Inject constructor(
    private val repository: CarsRepository
) {

}