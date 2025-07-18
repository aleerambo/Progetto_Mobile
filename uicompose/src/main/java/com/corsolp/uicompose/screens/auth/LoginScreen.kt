package com.corsolp.uicompose.screens.auth

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.corsolp.domain.models.User
import com.corsolp.uicompose.MainViewModel
import com.corsolp.uicompose.R

@Composable
fun LoginScreen(
    viewModel: AuthViewModel,
    mainViewModel: MainViewModel,
    onLoginSuccess: () -> Unit
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val authState by viewModel.authState.collectAsStateWithLifecycle()
    var showDialog by remember { mutableStateOf(false) }
    var dialogMessage by remember { mutableStateOf("") }

    LaunchedEffect(authState) {
        authState?.let {
            showDialog = true
            if (it.isSuccess) {
                val user = it.getOrNull()
                if (user != null) {
                    mainViewModel.login(user) // Aggiorna lo stato globale
                    dialogMessage = "Login effettuato con successo!"
                    onLoginSuccess() // Naviga alla schermata successiva
                }
            } else {
                dialogMessage = "Errore: ${it.exceptionOrNull()?.message ?: "Login fallito"}"
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(dimensionResource(R.dimen.spacing_medium)),
        verticalArrangement = Arrangement.Center
    ) {
        TextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.spacing_medium)))
        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.spacing_medium)))
        Button(
            onClick = { viewModel.login(email, password) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Login")
        }
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text("Risultato Login") },
            text = { Text(dialogMessage) },
            confirmButton = {
                Button(onClick = { showDialog = false }) {
                    Text("OK")
                }
            }
        )
    }
}
