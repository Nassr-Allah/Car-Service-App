package com.m2s.carservice.presentation.car_model_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.m2s.carservice.data.model.Reservation
import com.m2s.carservice.ui.materials.ScreenHeader
import com.m2s.carservice.ui.theme.LightGray
import com.m2s.carservice.ui.materials.CarModelCard
import com.m2s.carservice.ui.navigation.Routes
import com.m2s.carservice.data.model.Car
import com.m2s.carservice.data.model.CarModel

@Composable
fun ModelsScreen(navController: NavController, car: Car?) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(LightGray), contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ScreenHeader(title = "Modèles des Véhicules") {
                navController.popBackStack()
            }
            Spacer(modifier = Modifier.height(40.dp))
            ModelsList(list = car?.models ?: emptyList(), navController, car)
        }
    }
}

@Composable
fun ModelsList(list: List<CarModel>, navController: NavController, car: Car?) {
    LazyColumn(contentPadding = PaddingValues(20.dp)) {
        items(list) {
            CarModelCard(text = it.name, it.imgUrl) {
                val reservation = Reservation(carModel = it.name, carBrand = car?.brand ?: "null")
                navController.currentBackStackEntry?.arguments?.putParcelable("reservation", reservation)
                navController.currentBackStackEntry?.arguments?.putParcelable("car", car)
                navController.currentBackStackEntry?.arguments?.putParcelable("car_model", it)
                navController.navigate(Routes.ModelDetailsScreen.route)
            }
            Spacer(modifier = Modifier.height(15.dp))
        }
    }
}
