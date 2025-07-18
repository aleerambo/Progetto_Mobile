package com.corsolp.uicompose

import androidx.lifecycle.ViewModel
import com.corsolp.domain.models.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MainViewModel : ViewModel() {
    private val _isLoggedIn = MutableStateFlow(false)
    val isLoggedIn: StateFlow<Boolean> = _isLoggedIn

    private val _currentUser = MutableStateFlow<User?>(null)
    val currentUser: StateFlow<User?> = _currentUser

    fun login(user: User) {
        _currentUser.value = user
        _isLoggedIn.value = true
    }

    fun logout() {
        _currentUser.value = null
        _isLoggedIn.value = false
    }

    fun isAdmin(): Boolean {
        return _currentUser.value?.role == "admin"
    }

    fun deleteRentalPost(postId: Int): Unit {
        // Logic to delete a rental post by its ID
        // This would typically involve calling a use case or repository method
        // For now, we can just log the action or update the state accordingly
        println("Deleting rental post with ID: $postId")
        // You would implement the actual deletion logic here
    }
}