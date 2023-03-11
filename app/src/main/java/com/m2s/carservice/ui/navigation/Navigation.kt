package com.m2s.carservice.ui.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.m2s.carservice.presentation.car_model_screen.ModelsScreen
import com.m2s.carservice.presentation.cars_screen.CarsScreen
import com.m2s.carservice.presentation.confirmation_screen.ConfirmationScreen
import com.m2s.carservice.presentation.home_screen.HomeScreen
import com.m2s.carservice.presentation.map_screen.MapScreen
import com.m2s.carservice.presentation.model_details_screen.ModelDetailsScreen
import com.m2s.carservice.presentation.receipt_screen.ReceiptScreen
import com.m2s.carservice.presentation.service_screen.ServicesScreen
import com.m2s.carservice.data.model.Car
import com.m2s.carservice.data.model.CarModel
import com.m2s.carservice.data.model.Reservation


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.HomeScreen.route) {
        composable(route = Routes.HomeScreen.route) {
            HomeScreen(navController)
        }

        composable(route = Routes.MapScreen.route) {
            MapScreen(navController)
        }

        composable(route = Routes.CarsScreen.route) {
            CarsScreen(navController)
        }

        composable(route = Routes.ServiceScreen.route) {
            val reservation = navController.previousBackStackEntry?.arguments?.getParcelable<Reservation>("reservation")
            val car = navController.previousBackStackEntry?.arguments?.getParcelable<Car>("car")
            ServicesScreen(navController = navController, reservation, car)
        }

        composable(route = Routes.ConfirmationScreen.route) {
            val reservation = navController.previousBackStackEntry?.arguments?.getParcelable<Reservation>("reservation")
            ConfirmationScreen(navController, reservation)
        }

        composable(route = Routes.ReceiptScreen.route) {
            val reservation = navController.previousBackStackEntry?.
                    arguments?.getParcelable<Reservation>("reservation")
            ReceiptScreen(navController, reservation)
        }
        
        composable(route = Routes.ModelsScreen.route) {
            val car = navController.previousBackStackEntry?.arguments?.getParcelable<Car>("car")
            ModelsScreen(navController = navController, car)
        }
        
        composable(route = Routes.ModelDetailsScreen.route) {
            val reservation = navController.previousBackStackEntry?.arguments?.getParcelable<Reservation>("reservation")
            val car = navController.previousBackStackEntry?.arguments?.getParcelable<Car>("car")
            val carModel = navController.previousBackStackEntry?.arguments?.getParcelable<CarModel>("car_model")
            ModelDetailsScreen(navController = navController, reservation, car, carModel)
        }
    }
}