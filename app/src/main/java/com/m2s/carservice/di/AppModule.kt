package com.m2s.carservice.di

import com.m2s.carservice.data.api.NotificationApi
import com.m2s.carservice.data.api.ServerApi
import com.m2s.carservice.data.repositories.cars_repository.CarsRepository
import com.m2s.carservice.data.repositories.reservations_repository.ReservationsRepository
import com.m2s.carservice.data.repositories.cars_repository.CarsRepositoryImpl
import com.m2s.carservice.data.repositories.reservations_repository.ReservationsRepositoryImpl
import com.m2s.carservice.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideServerApi(): ServerApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ServerApi::class.java)
    }

    @Provides
    @Singleton
    fun provideNotificationApi(): NotificationApi {
        return Retrofit.Builder()
            .baseUrl(Constants.FIREBASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NotificationApi::class.java)
    }

    @Provides
    @Singleton
    fun provideCarsRepository(api: ServerApi): CarsRepository {
        return CarsRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideReservationsRepository(api: ServerApi): ReservationsRepository {
        return ReservationsRepositoryImpl(api)
    }

}