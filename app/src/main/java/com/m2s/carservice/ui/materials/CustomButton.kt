package com.m2s.carservice.ui.materials

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.m2s.carservice.ui.theme.DeepBlue

@Composable
fun CustomButton(text: String, icon: Int? = null, onClick: () -> Unit) {
    Button(
        onClick = { onClick() },
        modifier = Modifier.fillMaxWidth(0.8f),
        colors = ButtonDefaults.buttonColors(containerColor = DeepBlue),
        shape = RoundedCornerShape(10.dp)
    ) {
        Text(
            text = text, 
            style = MaterialTheme.typography.bodyLarge, 
            color = Color.White
        )
        if (icon != null) {
            Spacer(modifier = Modifier.width(15.dp))
            Icon(
                painter = painterResource(icon),
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(20.dp)
            )
        }
    }
}

@Preview
@Composable
fun ButtonPreview() {
    CustomButton(text = "OK") {
        
    }
}