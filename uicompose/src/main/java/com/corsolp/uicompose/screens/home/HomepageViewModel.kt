package com.corsolp.uicompose.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.corsolp.domain.models.Rental
import com.corsolp.domain.usecases.FetchAllRentalPostsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.dropWhile
import kotlinx.coroutines.launch

class HomepageViewModel(
    private val fetchAllRentalPostsUseCase: FetchAllRentalPostsUseCase,
): ViewModel() {

    private val _rentalList = MutableStateFlow<List<Rental>>(listOf())
    val rentalList: StateFlow<List<Rental>> = _rentalList

    private val _showLoader = MutableStateFlow(false)
    val showLoader: StateFlow<Boolean> = _showLoader

    init {
        fetchRentalTypeList()
    }

    private fun fetchRentalTypeList() {
        viewModelScope.launch {
            _showLoader.emit(true)
            fetchAllRentalPostsUseCase().dropWhile {
                it.isEmpty()
            }.collect { rentalList ->
                _rentalList.emit(rentalList)
                _showLoader.emit(false)
            }
        }
    }
}
