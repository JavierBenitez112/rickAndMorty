package com.example.rickandmorty.ui.login

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.rickandmorty.App.theme.RickAndMortyTheme
import com.example.rickandmorty.R


@Composable
fun LoginRoute(
    onLoginClick: () -> Unit

) {
    LoginScreen(
        onLoginClick = onLoginClick,
        modifier = Modifier.fillMaxSize()
    )
}

@Composable
private fun LoginScreen(
    onLoginClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier
        .fillMaxSize()
        .background(Color.White)
    ) {
        Column(modifier = Modifier.align(Alignment.Center)) {
            Image(
                painter = painterResource(id = R.drawable.rick),
                contentDescription = "Rick and Morty Logo"
            )
            Button(onClick = onLoginClick, modifier = Modifier.fillMaxWidth()) {
                Text("Entrar")
            }
        }

        Row(modifier = Modifier
            .fillMaxWidth()
            .align(Alignment.BottomCenter)
        ) {
            Text(
                text = "Javier Andre Benitez Garcia - #23405",
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewLoginScreen() {
    RickAndMortyTheme {
        Surface {
            LoginScreen(
                onLoginClick = {},
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}