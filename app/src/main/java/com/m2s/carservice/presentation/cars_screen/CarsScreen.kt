package com.m2s.carservice.presentation.cars_screen

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.m2s.carservice.ui.materials.ScreenHeader
import com.m2s.carservice.ui.theme.LightGray
import com.m2s.carservice.ui.materials.CarCard
import com.m2s.carservice.ui.navigation.Routes
import com.m2s.carservice.ui.theme.DeepBlue
import com.m2s.carservice.data.model.Car

@Composable
fun CarsScreen(navController: NavController, viewModel: CarsViewModel = hiltViewModel()) {
    val state = viewModel.state
    var isLoading by remember {
        mutableStateOf(false)
    }
    val context = LocalContext.current.applicationContext
    LaunchedEffect(key1 = state.value) {
        Log.d("CarsScreen", "state updated: ${state.value.toString()}")
        isLoading = state.value.isLoading
        if (state.value.error.isNotEmpty()) {
            Toast.makeText(context, state.value.error, Toast.LENGTH_SHORT).show()
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(LightGray),
        contentAlignment = Alignment.Center
    ) {
        if (isLoading) {
            CircularProgressIndicator(color = DeepBlue)
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(vertical = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ScreenHeader(title = "Liste des Véhicules") {
                    navController.popBackStack()
                }
                Spacer(modifier = Modifier.height(40.dp))
                CarsList(list = state.value.cars, navController)
            }
        }
    }
}

@Composable
fun CarsList(list: List<Car>, navController: NavController) {
    LazyColumn {
        items(list) { item ->
            CarCard(car = item) {
                navController.currentBackStackEntry?.arguments?.putParcelable("car", item)
                navController.navigate(Routes.ModelsScreen.route)
            }
            Spacer(modifier = Modifier.height(15.dp))
        }
    }
}
