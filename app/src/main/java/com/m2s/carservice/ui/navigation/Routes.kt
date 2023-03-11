package com.m2s.carservice.ui.navigation

sealed class Routes(val route: String) {
    object HomeScreen : Routes("home_screen")
    object MapScreen : Routes("map_screen")
    object ConfirmationScreen : Routes("confirmation_screen")
    object CarsScreen : Routes("cars_screen")
    object ServiceScreen : Routes("service_screen")
    object ReceiptScreen : Routes("receipt_screen")
    object ModelsScreen : Routes("models_screen")
    object ModelDetailsScreen : Routes("model_details_screen")
}
