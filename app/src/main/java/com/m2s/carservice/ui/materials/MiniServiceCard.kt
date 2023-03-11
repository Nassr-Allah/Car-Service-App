package com.m2s.carservice.ui.materials

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.m2s.carservice.data.model.MiniService
import com.m2s.carservice.ui.theme.DeepBlue

@Composable
fun MiniServiceCard(miniService: MiniService, onCheck: () -> Unit, onUncheck: () -> Unit) {
    Card(
        elevation = CardDefaults.cardElevation(5.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 5.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            MiniServiceInfo(miniService, onCheck, onUncheck)
        }
    }
}

@Composable
fun MiniServiceInfo(miniService: MiniService, onCheck: () -> Unit, onUncheck: () -> Unit) {
    var isCheck by remember {
        mutableStateOf(miniService.isChecked)
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(end = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(
                checked = isCheck,
                onCheckedChange = {
                    if (!isCheck) {
                        onCheck()
                    } else {
                        onUncheck()
                    }
                    isCheck = !isCheck
                },
                colors = CheckboxDefaults.colors(checkmarkColor = Color.White, checkedColor = DeepBlue)
            )
            Text(
                text = miniService.name,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Black,
                fontWeight = FontWeight.Bold
            )
        }

    }
}
