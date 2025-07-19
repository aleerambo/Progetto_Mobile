package com.corsolp.uicompose

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.corsolp.domain.models.User
import com.corsolp.domain.usecases.DeleteRentalPostUseCase
import com.corsolp.domain.usecases.LogoutUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MainViewModel(
    private val deleteRentalPostUseCase: DeleteRentalPostUseCase,
    private val logoutUseCase: LogoutUseCase
) : ViewModel() {
    private val _isLoggedIn = MutableStateFlow(false)
    val isLoggedIn: StateFlow<Boolean> = _isLoggedIn

    private val _currentUser = MutableStateFlow<User?>(null)
    val currentUser: StateFlow<User?> = _currentUser

    val userRole: StateFlow<String?> = _currentUser.map { it?.role }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = null
    )

    fun login(user: User) {
        _currentUser.value = user
        _isLoggedIn.value = true
    }

    fun logout() {
        println("MainViewModel: logout() CHIAMATO")
        viewModelScope.launch {
            val result = logoutUseCase()
            if (result.isSuccess) {
                _currentUser.value = null
                _isLoggedIn.value = false
            } else {
                // Gestisci eventuali errori di logout, ad esempio log o notifiche
                val errorMessage = result.exceptionOrNull()?.message ?: "Errore sconosciuto durante il logout"
                println(errorMessage)
            }
        }
    }

    fun isAdmin(): Boolean {
        return _currentUser.value?.role == "admin"
    }

    fun deleteRentalPost(postId: Int, onSuccess: () -> Unit, onError: (String) -> Unit) {
        viewModelScope.launch {
            val result = deleteRentalPostUseCase(postId)
            if (result.isSuccess) {
                onSuccess()
            } else {
                onError(result.exceptionOrNull()?.message ?: "Errore sconosciuto")
            }
        }
    }
}