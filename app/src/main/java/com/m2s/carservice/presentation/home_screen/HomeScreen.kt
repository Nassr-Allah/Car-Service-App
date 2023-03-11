package com.m2s.carservice.presentation.home_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.m2s.carservice.R
import com.m2s.carservice.ui.navigation.Routes
import com.m2s.carservice.ui.theme.DeepBlue
import com.m2s.carservice.ui.materials.CustomButton

@Composable
fun HomeScreen(navController: NavController) {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.White))
    {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Header()
            Buttons(navController)
        }
    }
}

@Composable
fun Header() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally)
    {

        Text(
            text = "BIENVENUE",
            style = MaterialTheme.typography.titleSmall,
            color = Color.Black,
            fontSize = 28.sp
        )
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = "CHEZ M2S",
            style = MaterialTheme.typography.titleLarge,
            color = DeepBlue,
            fontWeight = FontWeight.Black,
            fontSize = 36.sp
        )
        Image(
            painter = painterResource(R.drawable.logo_transparent),
            contentDescription = null,
            modifier = Modifier.fillMaxWidth(0.7f)
        )
    }
}

@Composable
fun Buttons(navController: NavController) {
    val handler = LocalUriHandler.current
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CustomButton(text = "Réserver") {
            navController.navigate(Routes.CarsScreen.route)
        }
        Spacer(modifier = Modifier.height(20.dp))
        CustomButton(text = "Map") {
            navController.navigate(Routes.MapScreen.route)
        }
        Spacer(modifier = Modifier.height(20.dp))
        CustomButton(text = "Visiter Notre Site Web") {
            handler.openUri("http://M2s-autodz.com")
        }
        Spacer(modifier = Modifier.height(20.dp))
        CustomButton(text = "Visiter Notre Page Facebook") {
            handler.openUri("https://www.facebook.com/abdou16mss/")
        }
    }
}
