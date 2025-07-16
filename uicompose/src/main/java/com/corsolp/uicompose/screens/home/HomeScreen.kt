package com.corsolp.uicompose.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.corsolp.domain.models.Rental
import com.corsolp.uicompose.R
import com.corsolp.uicompose.common.Loader

@Composable
fun HomeScreen(
    viewModel: HomepageViewModel,
    showDetails: (Rental) -> Unit
) {
    val showLoader       = viewModel.showLoader.collectAsStateWithLifecycle()
    val rentalList       = viewModel.rentalList.collectAsStateWithLifecycle()
    Box(modifier = Modifier.fillMaxSize()) {
        if (showLoader.value) Loader()
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.spacing_medium))
        ) {
            items(rentalList.value) { rental ->
                RentalCard(
                    rental = rental,
                    onItemClick = { showDetails(rental) },
                )
            }
        }
    }
}
