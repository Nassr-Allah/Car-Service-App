package com.m2s.carservice.presentation.model_details_screen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.m2s.carservice.R
import com.m2s.carservice.data.model.Car
import com.m2s.carservice.data.model.CarModel
import com.m2s.carservice.data.model.Reservation
import com.m2s.carservice.ui.materials.ScreenHeader
import com.m2s.carservice.ui.navigation.Routes
import com.m2s.carservice.ui.theme.DeepBlue
import com.m2s.carservice.ui.theme.LightGray
import com.m2s.carservice.ui.materials.CustomButton

var carYear = mutableStateOf("2008")
var carEngine = mutableStateOf("1.0")
var carEngineType = mutableStateOf("Essence")

@Composable
fun ModelDetailsScreen(
    navController: NavController,
    reservation: Reservation?,
    car: Car?,
    carModel: CarModel?
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(20.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        ScreenHeader(title = "Détails de Voiture") {
            navController.popBackStack()
        }
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround
        ) {
            DetailsForm(carModel)
            val res = Reservation(
                carYear = carYear.value,
                carEngine = carEngine.value,
                carBrand = reservation?.carBrand ?: "not received",
                carModel = reservation?.carModel ?: "not received",
                engineType = carEngineType.value
            )
            CustomButton(text = "Confirmer") {
                Log.d("ModelDetailsScreen", res.toString())
                navController.currentBackStackEntry?.arguments?.putParcelable("reservation", res)
                navController.currentBackStackEntry?.arguments?.putParcelable("car", car)
                navController.navigate(Routes.ServiceScreen.route)
            }
        }
    }
}

@Composable
fun DetailsForm(carModel: CarModel?) {
    val yearsList = mutableListOf<String>()
    for (i in 2008..2022) {
        yearsList.add(i.toString())
    }
    val engineTypeList = mutableListOf(
        "Essence", "Diesel"
    )
    val engines = mutableListOf(
        "1.0", "1.1", "1.2", "1.3", "1.4", "1.5", "1.6", "1.7", "1.8", "1.9", "2.0", "2.2", "2.5", "3.0"
    )
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        YearDropDownMenu(list = yearsList, label = "Année")
        Spacer(modifier = Modifier.height(20.dp))
        EngineDropDownMenu(list = engines, label = "Moteur")
        Spacer(modifier = Modifier.height(20.dp))
        EngineTypeDropDownMenu(list = engineTypeList, label = "Type de Moteur")
    }
}

@Composable
fun YearDropDownMenu(list: List<String>, label: String) {
    var expanded by remember {
        mutableStateOf(false)
    }
    var selectedIndex by remember {
        mutableStateOf(0)
    }
    Column {
        Text(
            text = label,
            style = MaterialTheme.typography.titleSmall,
            color = Color.Black,
            modifier = Modifier.padding(start = 20.dp)
        )
        Box(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .clip(RoundedCornerShape(10.dp))
                .background(LightGray),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = list[selectedIndex],
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { expanded = true }
                    .padding(vertical = 10.dp),
                style = MaterialTheme.typography.bodyLarge,
                color = Color.Black,
                textAlign = TextAlign.Center
            )
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.CenterEnd
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_arrow_down),
                    contentDescription = null,
                    tint = DeepBlue,
                    modifier = Modifier.size(25.dp)
                )
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .heightIn(max = 400.dp)
                    .background(Color.LightGray)
            ) {
                list.forEachIndexed { index, s ->
                    DropdownMenuItem(
                        text = {
                            Text(
                                text = s,
                                style = MaterialTheme.typography.bodyLarge,
                                color = Color.Black
                            )
                        },
                        onClick = {
                            carYear.value = s
                            selectedIndex = index
                            expanded = false
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun EngineDropDownMenu(list: List<String>, label: String) {
    var expanded by remember {
        mutableStateOf(false)
    }
    var selectedIndex by remember {
        mutableStateOf(0)
    }
    Column {
        Text(
            text = label,
            style = MaterialTheme.typography.titleSmall,
            color = Color.Black,
            modifier = Modifier.padding(start = 20.dp)
        )
        Box(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .clip(RoundedCornerShape(10.dp))
                .background(LightGray),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = list[selectedIndex],
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { expanded = true }
                    .padding(vertical = 10.dp),
                style = MaterialTheme.typography.bodyLarge,
                color = Color.Black,
                textAlign = TextAlign.Center
            )
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.CenterEnd
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_arrow_down),
                    contentDescription = null,
                    tint = DeepBlue,
                    modifier = Modifier.size(25.dp)
                )
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .heightIn(max = 400.dp)
                    .background(Color.LightGray)
            ) {
                list.forEachIndexed { index, engine ->
                    DropdownMenuItem(
                        text = {
                            Text(
                                text = engine,
                                style = MaterialTheme.typography.bodyLarge,
                                color = Color.Black
                            )
                        },
                        onClick = {
                            carEngine.value = engine
                            selectedIndex = index
                            expanded = false
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun EngineTypeDropDownMenu(list: List<String>, label: String) {
    var expanded by remember {
        mutableStateOf(false)
    }
    var selectedIndex by remember {
        mutableStateOf(0)
    }
    Column {
        Text(
            text = label,
            style = MaterialTheme.typography.titleSmall,
            color = Color.Black,
            modifier = Modifier.padding(start = 20.dp)
        )
        Box(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .clip(RoundedCornerShape(10.dp))
                .background(LightGray),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = list[selectedIndex],
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { expanded = true }
                    .padding(vertical = 10.dp),
                style = MaterialTheme.typography.bodyLarge,
                color = Color.Black,
                textAlign = TextAlign.Center
            )
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.CenterEnd
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_arrow_down),
                    contentDescription = null,
                    tint = DeepBlue,
                    modifier = Modifier.size(25.dp)
                )
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .heightIn(max = 400.dp)
                    .background(Color.LightGray)
            ) {
                list.forEachIndexed { index, s ->
                    DropdownMenuItem(
                        text = {
                            Text(
                                text = s,
                                style = MaterialTheme.typography.bodyLarge,
                                color = Color.Black
                            )
                        },
                        onClick = {
                            carEngineType.value = s
                            selectedIndex = index
                            expanded = false
                        }
                    )
                }
            }
        }
    }
}


