package com.m2s.carservice.presentation.service_screen

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
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
import com.m2s.carservice.data.model.Car
import com.m2s.carservice.data.model.MiniService
import com.m2s.carservice.data.model.Reservation
import com.m2s.carservice.ui.materials.ScreenHeader
import com.m2s.carservice.ui.theme.LightGray
import com.m2s.carservice.ui.materials.ServiceCard
import com.m2s.carservice.ui.navigation.Routes
import com.m2s.carservice.ui.theme.DeepBlue
import com.m2s.carservice.ui.materials.MiniServiceCard
import com.m2s.carservice.ui.materials.CustomButton

var servicesList = mutableListOf<MiniService>()

@Composable
fun ServicesScreen(
    navController: NavController,
    reservation: Reservation?,
    car: Car?,
    viewModel: ServiceViewModel = hiltViewModel()
) {
    val state = viewModel.state
    var isLoading by remember {
        mutableStateOf(false)
    }
    val context = LocalContext.current.applicationContext
    LaunchedEffect(key1 = state.value) {
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
            val map = mutableMapOf<String, List<MiniService>>()
            for (service in state.value.data) {
                map.put(service.name, service.miniServices)
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ScreenHeader(title = "Les Services") {
                    navController.popBackStack()
                }
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(vertical = 20.dp, horizontal = 20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    ServicesList(
                        group = map,
                        navController = navController
                    )
                    CustomButton(text = "Confirmer") {
                        Log.d("ServiceScreen", servicesList.toString())
                        val res = Reservation(
                            carModel = reservation?.carModel ?: "not received",
                            carBrand = reservation?.carBrand ?: "not received",
                            carEngine = reservation?.carEngine ?: "not received",
                            carYear = reservation?.carYear ?: "not received",
                            services = servicesList,
                            engineType = reservation?.engineType ?: "not received"
                        )
                        navController.currentBackStackEntry?.arguments?.putParcelable(
                            "reservation",
                            res
                        )
                        navController.currentBackStackEntry?.arguments?.putParcelable("car", car)
                        navController.navigate(Routes.ConfirmationScreen.route)
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ServicesList(group: Map<String, List<MiniService>>, navController: NavController) {
    LazyColumn(modifier = Modifier.fillMaxHeight(0.85f)) {
        group.forEach { (service, miniServices) ->
            stickyHeader {
                ServiceCard(service = service)
            }
            items(miniServices) { miniService ->
                MiniServiceCard(
                    miniService = miniService,
                    onCheck = {
                        miniService.isChecked = true
                        servicesList.add(miniService)
                        Log.d("ServiceScreen", "checked")
                        Log.d("ServiceScreen", "added $miniService to $servicesList")
                    },
                    onUncheck = {
                        miniService.isChecked = false
                        servicesList.remove(miniService)
                    }
                )
                Spacer(modifier = Modifier.height(7.dp))
            }
        }
    }
}
