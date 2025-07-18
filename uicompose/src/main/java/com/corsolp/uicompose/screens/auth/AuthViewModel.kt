package com.corsolp.uicompose.screens.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.corsolp.domain.models.User
import com.corsolp.domain.usecases.LoginUseCase
import com.corsolp.domain.usecases.RegisterUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AuthViewModel(
    private val loginUseCase: LoginUseCase,
    private val registerUseCase: RegisterUseCase
) : ViewModel() {

    private val _authState = MutableStateFlow<Result<User>?>(null)
    val authState: StateFlow<Result<User>?> = _authState

    fun login(mail: String, password: String) {
        viewModelScope.launch {
            _authState.value = loginUseCase(mail, password)
        }
    }

    fun register(
        nome: String,
        cognome: String,
        telefono: String,
        mail: String,
        password: String
    ) {
        viewModelScope.launch {
            println("AuthViewModel: Registering user: $nome $cognome, $mail")
            _authState.value = registerUseCase(nome, cognome, telefono, mail, password)
        }
    }
}