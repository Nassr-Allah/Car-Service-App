package com.m2s.carservice.ui.materials

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.m2s.carservice.R
import com.m2s.carservice.ui.theme.DeepBlue
import com.m2s.carservice.ui.theme.LightGray
import com.m2s.carservice.ui.theme.SoftGray
import com.m2s.carservice.ui.theme.SoftRed

@Composable
fun ServiceCard(service: String) {
    Card(
        elevation = CardDefaults.cardElevation(0.dp),
        colors = CardDefaults.cardColors(containerColor = LightGray),
        modifier = Modifier.fillMaxWidth(),
        shape = RectangleShape
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 5.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = service,
                style = MaterialTheme.typography.bodyLarge,
                color = DeepBlue,
                fontWeight = FontWeight.Bold
            )
        }
    }
}
