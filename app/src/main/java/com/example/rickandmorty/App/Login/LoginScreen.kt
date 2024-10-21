package com.example.rickandmorty.ui.login

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.rickandmorty.App.Login.DataStoreLoginState
import com.example.rickandmorty.App.Login.LoginEvent
import com.example.rickandmorty.App.Login.LoginViewModel
import com.example.rickandmorty.App.theme.RickAndMortyTheme
import com.example.rickandmorty.R
import kotlin.reflect.KFunction1


@Composable
fun LoginRoute(
    onLoginClick: () -> Unit,
    viewModel: LoginViewModel = viewModel(factory = LoginViewModel.Factory)
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LoginScreen(
        onLoginClick = {
            if (state.name.isNotEmpty()) {
                viewModel.onEvent(LoginEvent.SaveName)
            }else{
                viewModel.onEvent(LoginEvent.UserNameChanged("Unknown"))
                viewModel.onEvent(LoginEvent.SaveName)
            }
            onLoginClick()
        },
        modifier = Modifier.fillMaxSize(),
        state = state,
        onNameChange = { viewModel.onEvent(LoginEvent.UserNameChanged(it)) },
    )
}

@Composable
private fun LoginScreen(
    onLoginClick: () -> Unit,
    modifier: Modifier = Modifier,
    state: DataStoreLoginState,
    onNameChange: (String) -> Unit,
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
            OutlinedTextField(
                value = state.name,
                onValueChange = onNameChange,
                label = { Text("Nombre") },
                modifier = Modifier.fillMaxWidth().padding(16.dp)
            )
            Button(
                onClick = onLoginClick,
                modifier = Modifier.fillMaxWidth().padding(16.dp)
            ) {
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
                modifier = Modifier.fillMaxSize(),
                state = DataStoreLoginState(),
                onNameChange = {},
            )
        }
    }
}