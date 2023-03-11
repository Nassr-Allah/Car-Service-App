package com.m2s.carservice.presentation.confirmation_screen

import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.m2s.carservice.ui.materials.ScreenHeader
import com.m2s.carservice.ui.navigation.Routes
import com.m2s.carservice.ui.theme.DeepBlue
import com.m2s.carservice.ui.theme.LightGray
import com.m2s.carservice.ui.theme.SoftGray
import com.m2s.carservice.data.model.Reservation
import com.m2s.carservice.data.model.toReservationDto
import com.m2s.carservice.ui.materials.CustomButton
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.date.DatePickerDefaults
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.datetime.time.TimePickerDefaults
import com.vanpra.composematerialdialogs.datetime.time.timepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ConfirmationScreen(
    navController: NavController,
    reservation: Reservation?,
    viewModel: ConfirmationViewModel = hiltViewModel()
) {
    Log.d("ConfirmationScreen", reservation.toString())
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(20.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        ScreenHeader(title = "Confirmer La Réservation") {
            navController.popBackStack()
        }
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Form(reservation, viewModel, navController = navController)
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Form(res: Reservation?, viewModel: ConfirmationViewModel = hiltViewModel(), navController: NavController) {
    val state = viewModel.state
    var name by remember {
        mutableStateOf("")
    }
    var phoneNumber by remember {
        mutableStateOf("")
    }
    var selectedDate by remember {
        mutableStateOf("CHOISIR LA DATE")
    }
    var selectedTime by remember {
        mutableStateOf("CHOISIR LE TEMPS")
    }
    var isLoading by remember {
        mutableStateOf(false)
    }
    val dateDialogState = rememberMaterialDialogState()
    val timeDialogState = rememberMaterialDialogState()
    var reservation by remember {
        mutableStateOf(res)
    }
    val context = LocalContext.current.applicationContext

    LaunchedEffect(key1 = state.value) {
        isLoading = state.value.isLoading
        if (state.value.data != null && state.value.data!!.isSuccessful) {
            navController.navigate(Routes.ReceiptScreen.route)
        }
        if (state.value.error.isNotEmpty()) {
            Toast.makeText(context, state.value.error, Toast.LENGTH_SHORT).show()
        }
    }

    MaterialDialog(
        dialogState = dateDialogState,
        buttons = {
            positiveButton("OK")
            negativeButton("ANNULER")
        },
    ) {
        datepicker(
            colors = DatePickerDefaults.colors(
                dateActiveBackgroundColor = DeepBlue,
                headerBackgroundColor = DeepBlue
            )
        ) {
            selectedDate = DateTimeFormatter
                .ofPattern("dd MMMM yyyy", java.util.Locale.forLanguageTag("fr"))
                .format(it)
        }
    }
    MaterialDialog(
        dialogState = timeDialogState,
        buttons = {
            positiveButton("OK")
            negativeButton("ANNULER")
        }
    ) {
        timepicker(
            is24HourClock = true,
            colors = TimePickerDefaults.colors(
                selectorColor = DeepBlue,
                activeBackgroundColor = DeepBlue
            )
        ) {
            selectedTime = DateTimeFormatter
                .ofPattern("HH:mm")
                .format(it)
        }
    }
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = name,
            onValueChange = {
                name = it
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                containerColor = LightGray,
                textColor = Color.Black,
                unfocusedLabelColor = SoftGray,
                focusedLabelColor = DeepBlue,
                cursorColor = DeepBlue,
                unfocusedBorderColor = Color.Transparent,
                focusedBorderColor = DeepBlue
            ),
            maxLines = 1,
            shape = RoundedCornerShape(7.dp),
            modifier = Modifier.fillMaxWidth(0.8f),
            label = {
                Text(text = "NOM ET PRENOM", style = MaterialTheme.typography.bodyLarge)
            }
        )
        Spacer(modifier = Modifier.height(20.dp))
        OutlinedTextField(
            value = phoneNumber,
            onValueChange = {
                phoneNumber = it
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                containerColor = LightGray,
                textColor = Color.Black,
                unfocusedLabelColor = SoftGray,
                focusedLabelColor = DeepBlue,
                cursorColor = DeepBlue,
                unfocusedBorderColor = Color.Transparent,
                focusedBorderColor = DeepBlue
            ),
            maxLines = 1,
            shape = RoundedCornerShape(7.dp),
            modifier = Modifier.fillMaxWidth(0.8f),
            label = {
                Text(text = "NUMERO DE TELEPHONE", style = MaterialTheme.typography.bodyLarge)
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = selectedDate,
            style = MaterialTheme.typography.bodyLarge,
            color = Color.Black,
            modifier = Modifier
                .clip(RoundedCornerShape(7.dp))
                .background(LightGray)
                .fillMaxWidth(0.8f)
                .padding(horizontal = 15.dp, vertical = 15.dp)
                .clickable {
                    dateDialogState.show()
                }
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = selectedTime,
            style = MaterialTheme.typography.bodyLarge,
            color = Color.Black,
            modifier = Modifier
                .clip(RoundedCornerShape(7.dp))
                .background(LightGray)
                .fillMaxWidth(0.8f)
                .padding(horizontal = 15.dp, vertical = 15.dp)
                .clickable {
                    timeDialogState.show()
                }
        )
        Spacer(modifier = Modifier.height(70.dp))
        if (isLoading) {
            CircularProgressIndicator(color = DeepBlue)
        } else {
            CustomButton(text = "Confirmer") {
                reservation?.apply {
                    clientName = name
                    clientPhone = phoneNumber
                    date = selectedDate
                    time = selectedTime
                }
                Log.d("ConfirmationScreen", reservation?.toReservationDto().toString())
                viewModel.createReservation(reservation!!.toReservationDto())
                navController.currentBackStackEntry?.arguments?.putParcelable("reservation", reservation)
            }
        }
    }
}