package com.m2s.carservice.presentation.receipt_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.m2s.carservice.R
import com.m2s.carservice.ui.navigation.Routes
import com.m2s.carservice.ui.theme.DeepBlue
import com.m2s.carservice.ui.theme.LightGray
import com.m2s.carservice.ui.theme.SoftGray
import com.m2s.carservice.data.model.Reservation

@Composable
fun ReceiptScreen(
    navController: NavController,
    reservation: Reservation?,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(LightGray)
            .padding(10.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround
        ) {
            Receipt(reservation)
            Button(
                onClick = {
                    navController.navigate(Routes.HomeScreen.route) {
                        popUpTo(Routes.HomeScreen.route)
                    }
                },
                modifier = Modifier.fillMaxWidth(0.9f),
                colors = ButtonDefaults.buttonColors(containerColor = DeepBlue),
                shape = RoundedCornerShape(10.dp)
            ) {
                Text(
                    text = "OK",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.White,
                    modifier = Modifier.padding(vertical = 5.dp)
                )
            }
        }
    }
}

@Composable
fun Receipt(reservation: Reservation?) {
    Card(
        shape = RoundedCornerShape(5.dp),
        elevation = CardDefaults.cardElevation(5.dp),
        modifier = Modifier
            .fillMaxWidth(0.9f)
            .fillMaxHeight(0.7f),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            ReceiptHeader()
            ReceiptDetails(reservation)
            ReceiptFooter()
        }
    }
}

@Composable
fun ReceiptHeader() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            shape = CircleShape,
            colors = CardDefaults.cardColors(containerColor = LightGray)
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_check),
                contentDescription = null,
                tint = DeepBlue,
                modifier = Modifier
                    .padding(20.dp)
                    .size(30.dp)
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "Votre Réservation est Envoyé",
            style = MaterialTheme.typography.titleLarge,
            color = Color.Black
        )
    }
}

@Composable
fun ReceiptDetails(reservation: Reservation?) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .border(width = 1.dp, color = SoftGray, shape = RoundedCornerShape(10.dp))
            .padding(20.dp)
    ) {
        DetailRow(text = reservation?.clientName ?: "")
        Spacer(modifier = Modifier.height(10.dp))
        DetailRow(text = "${reservation?.carBrand} ${reservation?.carModel}")
        Spacer(modifier = Modifier.height(10.dp))
        DetailRow(text = reservation?.clientPhone ?: "")
        Spacer(modifier = Modifier.height(10.dp))
        DetailRow(text = "${reservation?.date} - ${reservation?.time}")
    }
}

@Composable
fun DetailRow(text: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(modifier = Modifier
            .size(9.dp)
            .clip(CircleShape)
            .background(DeepBlue)
        )
        Spacer(modifier = Modifier.width(15.dp))
        Text(
            text = text,
            style = MaterialTheme.typography.bodyLarge,
            color = Color.Black
        )
    }
}

@Composable
fun ReceiptFooter() {
    Text(
        text = "Nous allons vous répondre dans les plus brefs délais!",
        style = MaterialTheme.typography.titleLarge,
        color = Color.Black,
        minLines = 2,
        fontWeight = FontWeight.Black,
        modifier = Modifier.fillMaxWidth(0.8f),
        textAlign = TextAlign.Center
    )
}