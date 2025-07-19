package com.corsolp.uicompose.screens.create

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.corsolp.domain.usecases.CreateRentalPostUseCase
import com.corsolp.domain.usecases.FetchNeighborhoodListUseCase
import com.corsolp.domain.usecases.FetchRentalTypeListUseCase
import com.corsolp.domain.usecases.FetchServiceListUseCase

class CreateRentalViewModelFactory(
    private val createRentalPostUseCase: CreateRentalPostUseCase,
    private val fetchServiceListUseCase: FetchServiceListUseCase,
    private val fetchNeighborhoodListUseCase: FetchNeighborhoodListUseCase,
    private val fetchRentalTypeListUseCase: FetchRentalTypeListUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CreateRentalViewModel::class.java)) {
            return CreateRentalViewModel(
                createRentalPostUseCase,
                fetchServiceListUseCase,
                fetchNeighborhoodListUseCase,
                fetchRentalTypeListUseCase
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }
}