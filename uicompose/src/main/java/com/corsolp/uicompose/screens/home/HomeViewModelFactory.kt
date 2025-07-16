package com.corsolp.uicompose.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.corsolp.domain.usecases.FetchRentalTypeListUseCase

class HomeViewModelFactory(
    private val fetchUseCase: FetchRentalTypeListUseCase,
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomepageViewModel::class.java)) {
            return HomepageViewModel(
                fetchUseCase,
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}