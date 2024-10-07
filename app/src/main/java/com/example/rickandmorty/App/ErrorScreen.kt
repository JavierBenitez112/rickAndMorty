package com.example.rickandmorty.App

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ErrorScreen(errorMessage: String, onRetry: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF6F6F6)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Icono de advertencia
        Box(
            modifier = Modifier
                .size(64.dp)
                .background(Color.Red, shape = CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "!",
                fontSize = 32.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Mensaje de error
        Text(
            text = errorMessage,
            textAlign = TextAlign.Center,
            color = Color(0xFFB00020),
            fontSize = 16.sp
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Botón de reintentar
        Button(
            onClick = onRetry,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White,
                contentColor = Color.Black
            ),
            shape = MaterialTheme.shapes.small
        ) {
            Text("Reintentar")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewErrorScreen() {
    ErrorScreen(errorMessage = "Error al obtener listado de personajes.\nIntenta de nuevo", onRetry = {})
}