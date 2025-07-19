package com.corsolp.uicompose

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.corsolp.domain.usecases.DeleteRentalPostUseCase
import com.corsolp.domain.usecases.LogoutUseCase

class MainViewModelFactory(
    private val deleteRentalPostUseCase: DeleteRentalPostUseCase,
    private val logoutUseCase: LogoutUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(
                deleteRentalPostUseCase,
                logoutUseCase
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}