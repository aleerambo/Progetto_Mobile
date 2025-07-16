package com.corsolp.uicompose.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.corsolp.domain.models.Rental
import com.corsolp.domain.usecases.FetchRentalTypeListUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.dropWhile
import kotlinx.coroutines.launch

class HomepageViewModel(
    private val fetchRentalTypeListUseCase: FetchRentalTypeListUseCase,
): ViewModel() {

    private val _rentalTypeList = MutableStateFlow<List<Rental>>(listOf())
    val rentalTypeList: StateFlow<List<Rental>> = _rentalTypeList

    private val _showLoader = MutableStateFlow(false)
    val showLoader: StateFlow<Boolean> = _showLoader

    init {
        fetchRentalTypeList()
    }

    private fun fetchRentalTypeList() {
        viewModelScope.launch {
            _showLoader.emit(true)
            fetchRentalTypeListUseCase().dropWhile {
                it.isEmpty()
            }.collect { rentalTypeList ->
                _rentalTypeList.emit(rentalTypeList)
                _showLoader.emit(false)
            }
        }
    }
}
