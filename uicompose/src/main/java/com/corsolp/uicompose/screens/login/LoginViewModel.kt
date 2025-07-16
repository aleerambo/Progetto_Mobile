package com.corsolp.uicompose.screens.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.corsolp.domain.models.User
import com.corsolp.domain.usecases.LoginUseCase
import com.corsolp.domain.usecases.RegisterUseCase

class LoginViewModel(
    private val loginUseCase: LoginUseCase,
    private val registerUseCase: RegisterUseCase
) : ViewModel() {

    fun doLogin(mail: String, password: String): LiveData<Result<User>> = liveData {
        emit(loginUseCase(mail, password))
    }

    fun doRegister(
        nome: String, cognome: String, mail: String, telefono: String, password: String
    ): LiveData<Result<User>> = liveData {
        emit(registerUseCase(nome, cognome, mail, telefono, password))
    }
}
