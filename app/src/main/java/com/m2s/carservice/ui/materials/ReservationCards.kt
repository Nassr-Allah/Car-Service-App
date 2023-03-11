package com.m2s.carserviceadmin.ui.materials

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
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
fun ReservationCard(onCallClick: () -> Unit, onDeleteClick: () -> Unit) {
    Card(
        elevation = CardDefaults.cardElevation(7.dp),
        modifier = Modifier.fillMaxWidth(0.8f),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(10.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 15.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(0.8f),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Text",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "10000 DA",
                    style = MaterialTheme.typography.bodyLarge,
                    color = DeepBlue,
                    fontWeight = FontWeight.Bold
                )
            }
            Divider(
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .padding(vertical = 5.dp),
                thickness = 1.dp,
                color = SoftGray
            )

            Spacer(modifier = Modifier.height(10.dp))
            DetailRow(text = "Some Text")
            Spacer(modifier = Modifier.height(5.dp))
            DetailRow(text = "Some Text")
            Spacer(modifier = Modifier.height(5.dp))
            DetailRow(text = "Some Text")
            Spacer(modifier = Modifier.height(5.dp))
            DetailRow(text = "Some Text")
            Spacer(modifier = Modifier.height(30.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextWithIcon(text = "APPELER", icon = R.drawable.ic_phone, color = DeepBlue) {
                    onCallClick()
                }
                Spacer(modifier = Modifier.width(15.dp))
                TextWithIcon(text = "SUPPRIMER", icon = R.drawable.ic_delete, color = SoftRed) {
                    onDeleteClick()
                }
            }
        }
    }
}

@Composable
fun TextWithIcon(text: String, icon: Int, color: Color, onClick: () -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.clickable { onClick() },
        horizontalArrangement = Arrangement.Start
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Bold,
            color = color
        )
        Spacer(modifier = Modifier.width(5.dp))
        Icon(
            painter = painterResource(icon),
            contentDescription = null,
            tint = color,
            modifier = Modifier.size(18.dp)
        )
    }
}

@Composable
fun DetailRow(text: String) {
    Row(
        modifier = Modifier.fillMaxWidth(0.8f),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(modifier = Modifier
            .clip(CircleShape)
            .size(7.dp)
            .background(DeepBlue)
        )
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            text = text,
            style = MaterialTheme.typography.bodyLarge,
            color = Color.Black
        )
    }
}

@Preview
@Composable
fun CardPreview() {
    ReservationCard(onCallClick = {}, onDeleteClick = {})
}