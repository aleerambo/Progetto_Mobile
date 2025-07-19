package com.corsolp.uicompose.screens.auth

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.corsolp.uicompose.MainViewModel
import com.corsolp.uicompose.R

@Composable
fun LoginScreen(
    viewModel: AuthViewModel,
    mainViewModel: MainViewModel,
    onLoginSuccess: () -> Unit,
    onNavigateToRegister: () -> Unit // Callback per navigare alla registrazione
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val authState by viewModel.authState.collectAsStateWithLifecycle()
    var showDialog by remember { mutableStateOf(false) }
    var dialogMessage by remember { mutableStateOf("") }

    val loginSuccessMessage = stringResource(R.string.login_success)
    val loginFailedMessage = stringResource(R.string.login_failed)

    LaunchedEffect(authState) {
        authState?.let {
            showDialog = true
            if (it.isSuccess) {
                val user = it.getOrNull()
                if (user != null) {
                    mainViewModel.login(user)
                    dialogMessage = loginSuccessMessage
                    onLoginSuccess()
                }
            } else {
                dialogMessage = loginFailedMessage
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(dimensionResource(R.dimen.spacing_medium))
            .verticalScroll(rememberScrollState()),
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
        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.spacing_medium)))
        Text(stringResource(R.string.invite_to_register))
        Button(
            onClick = { onNavigateToRegister() }, // Naviga alla registrazione
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(R.color.blue))
        ) {
            Text(stringResource(R.string.register), color = colorResource(R.color.white))
        }
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text(stringResource(R.string.login_result)) },
            text = { Text(dialogMessage) },
            confirmButton = {
                Button(onClick = { showDialog = false }) {
                    Text("OK")
                }
            }
        )
    }
}
