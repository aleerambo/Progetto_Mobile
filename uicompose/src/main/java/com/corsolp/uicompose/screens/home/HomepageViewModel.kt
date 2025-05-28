package com.corsolp.uicompose.screens.home

import androidx.lifecycle.ViewModel
import com.corsolp.domain.models.RentalType
import com.corsolp.domain.usecases.FetchRentalTypeListUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.dropWhile
import kotlinx.coroutines.launch

class HomepageViewModel(
    private val fetchRentalTypeListUseCase: FetchRentalTypeListUseCase,
    private val startFetchRentalTypeListUseCase: StartFetchRentalTypeListUseCase
): ViewModel() {

    private val _accomodationTypeList = MutableStateFlow<List<RentalType>>(listOf())
    val accomodationTypeList: StateFlow<List<RentalType>> = _accomodationTypeList

    private val _showLoader = MutableStateFlow(false)
    val showLoader: StateFlow<Boolean> = _showLoader

    init {
        fetchRentalTypeList()
    }

    fun startFetchRentalTypeList() {
        //TODO qui sarebbe ideale passare la città dall'UI (es. combo box oppure dopo aver ottenuto la posizione attuale del dispositivo)
        viewModelScope.launch {
            startFetchRentalTypeListUseCase.invoke("Cesena")
        }
    }

    private fun fetchRentalTypeList() {
        viewModelScope.launch {
            _showLoader.emit(true)
            fetchRentalTypeListUseCase().dropWhile {
                it.isEmpty()
            }.collect { accomodationTypeList ->
                _accomodationTypeList.emit(accomodationTypeList)
                _showLoader.emit(false)
            }
        }
    }
}
