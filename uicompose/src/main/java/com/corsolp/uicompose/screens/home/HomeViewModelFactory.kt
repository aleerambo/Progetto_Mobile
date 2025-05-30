package com.corsolp.ui.compose.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.corsolp.domain.usecases.FetchAccomodationTypeListUseCase
import com.corsolp.domain.usecases.StartFetchAccomadationTypeListUseCase

class HomeViewModelFactory(
    private val fetchUseCase: FetchAccomodationTypeListUseCase,
    private val startFetchUseCase: StartFetchAccomadationTypeListUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomepageViewModel::class.java)) {
            return HomepageViewModel(
                fetchUseCase,
                startFetchUseCase
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}