package com.corsolp.uicompose.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.corsolp.domain.models.RentalType
import com.corsolp.ui.compose.screens.home.RentalCard
import com.corsolp.uicompose.common.Loader

@Composable
fun HomeScreen(
    viewModel: HomepageViewModel,
    showDetails: (RentalType) -> Unit
) {
    val showLoader = viewModel.showLoader.collectAsStateWithLifecycle()
    val rentalTypeList = viewModel.rentalTypeList.collectAsStateWithLifecycle()

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        if (showLoader.value) Loader()

        LazyColumn{
            items(rentalTypeList.value.size) { itemIndex ->
                val rentalType = rentalTypeList.value[itemIndex]
                RentalCard(
                    rentalType = rentalType,
                    onItemClick = { rentalType ->
                        showDetails(rentalType)
                    }
                )
            }
        }
    }

    LaunchedEffect(key1 = Unit) {
        viewModel.startFetchRentalTypeList()
    }
}
