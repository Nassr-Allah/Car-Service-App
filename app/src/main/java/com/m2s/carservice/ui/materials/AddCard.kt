package com.m2s.carservice.ui.materials

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.m2s.carservice.R
import com.m2s.carservice.ui.theme.DeepBlue
import com.m2s.carservice.ui.theme.LightGray
import com.m2s.carservice.ui.theme.SoftGray

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddCard(label: String, isServices: Boolean = false, alpha: Float, onClick: () -> Unit, onBackClick: () -> Unit) {
    var carModel by remember {
        mutableStateOf("")
    }
    var price by remember {
        mutableStateOf("")
    }
    var isPriceValid by remember {
        mutableStateOf(true)
    }
    var isValid by remember {
        mutableStateOf(true)
    }
    Card(
        elevation = CardDefaults.cardElevation(10.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = Modifier
            .fillMaxWidth(0.9f)
            .fillMaxHeight(0.7f)
            .alpha(alpha)
    ) {
        Box(
            modifier = Modifier.padding(20.dp), contentAlignment = Alignment.TopStart
        ) {
            Card(
                elevation = CardDefaults.cardElevation(7.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                shape = CircleShape,
                onClick = {
                    onBackClick()
                }
            ) {
                Box(
                    modifier = Modifier.padding(8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_back),
                        contentDescription = null,
                        tint = Color.Black,
                        modifier = Modifier
                            .size(20.dp)
                    )
                }
            }
        }
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround
        ) {
            OutlinedTextField(
                value = carModel,
                onValueChange = {
                    carModel = it
                    isValid = true
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
                isError = !isValid,
                modifier = Modifier.fillMaxWidth(0.8f),
                label = {
                    Text(text = label, style = MaterialTheme.typography.bodyLarge)
                }
            )
            if (isServices) {
                OutlinedTextField(
                    value = price,
                    onValueChange = {
                        price = it
                        isPriceValid = true
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
                    isError = !isValid,
                    modifier = Modifier.fillMaxWidth(0.8f),
                    label = {
                        Text(text = "PRIX", style = MaterialTheme.typography.bodyLarge)
                    }
                )
            }
            CustomButton(text = "Confirmer") {
                if (carModel.isEmpty()) {
                    isValid = false
                } else {
                    isValid = true
                    onClick()
                }
            }
        }
    }
}