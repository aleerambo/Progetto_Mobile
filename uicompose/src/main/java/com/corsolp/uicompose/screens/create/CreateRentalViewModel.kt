package com.corsolp.uicompose.screens.create

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.corsolp.domain.models.Neighborhood
import com.corsolp.domain.models.RentalType
import com.corsolp.domain.models.Service
import com.corsolp.domain.usecases.CreateRentalPostUseCase
import com.corsolp.domain.usecases.FetchNeighborhoodListUseCase
import com.corsolp.domain.usecases.FetchRentalTypeListUseCase
import com.corsolp.domain.usecases.FetchServiceListUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.dropWhile
import kotlinx.coroutines.launch

class CreateRentalViewModel(
    private val createRentalPostUseCase: CreateRentalPostUseCase,
    private val fetchServiceListUseCase: FetchServiceListUseCase,
    private val fetchNeighborhoodListUseCase: FetchNeighborhoodListUseCase,
    private val fetchRentalTypeListUseCase: FetchRentalTypeListUseCase
) : ViewModel() {

    private val _services = MutableStateFlow<List<Service>>(emptyList())
    val services: StateFlow<List<Service>> = _services

    private val _neighborhood = MutableStateFlow<List<Neighborhood>>(emptyList())
    val neighborhood: StateFlow<List<Neighborhood>> = _neighborhood

    private val _rentalTypes = MutableStateFlow<List<RentalType>>(emptyList())
    val rentalTypes: StateFlow<List<RentalType>> = _rentalTypes

    private val _creationState = MutableStateFlow<Result<Unit>?>(null)
    val creationState: StateFlow<Result<Unit>?> = _creationState

    init {
        fetchServices()
        fetchAreas()
        fetchRentalTypes()
    }

    private fun fetchServices() {
        viewModelScope.launch {
            fetchServiceListUseCase().dropWhile {
                it.isEmpty()
            }.collect { serviceList ->
                _services.emit(serviceList)
            }
        }
    }

    private fun fetchAreas() {
        viewModelScope.launch {
            fetchNeighborhoodListUseCase().dropWhile {
                it.isEmpty()
            }.collect { areaList ->
                _neighborhood.emit(areaList)
            }
        }
    }

    private fun fetchRentalTypes() {
        viewModelScope.launch {
            fetchRentalTypeListUseCase().dropWhile {
                it.isEmpty()
            }.collect { rentalTypeList ->
                _rentalTypes.emit(rentalTypeList)
            }
        }
    }

    fun createRentalPost(
        idArea: Int,
        price: Double,
        description: String,
        rooms: Int,
        squareMeters: Int,
        floor: Int,
        address: String,
        selectedServices: String,
        type: Int,
        numberOfTenants: Int,
        minContract: Int,
        maxContract: Int
    ) {
        viewModelScope.launch {
            _creationState.value = createRentalPostUseCase(
                idArea,
                price,
                description,
                rooms,
                squareMeters,
                floor,
                address,
                selectedServices,
                type,
                numberOfTenants,
                minContract,
                maxContract
            )
        }
    }
}