package com.corsolp.uicompose

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.corsolp.domain.di.UseCaseProvider
import com.corsolp.domain.models.RentalType
import com.corsolp.uicompose.screens.details.DetailsScreen
import com.corsolp.uicompose.screens.home.HomeScreen
import com.corsolp.uicompose.screens.home.HomeViewModelFactory
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Composable
fun NavigationHost() {
    val navController = rememberNavController()

    NavHost(
        navController,
        startDestination = Home
    ) {
        composable<Home> {
            HomeScreen(
                viewModel = viewModel(
                    factory = HomeViewModelFactory(
                        UseCaseProvider.fetchRentalTypeListUseCase,
                        UseCaseProvider.startFetchRentalTypeListUseCase
                    )
                ),
                showDetails = { rentalType ->
                    val rentalTypeJsonString = Json.encodeToString(rentalType)
                    navController.navigate(
                        DetailsScreen(rentalTypeJsonString)
                    )
                }
            )
        }
        composable<DetailsScreen> { navBackStackEntry ->
            val detailsScreen = navBackStackEntry.toRoute<DetailsScreen>()
            val rentalType = Json.decodeFromString<RentalType>(
                detailsScreen.rentalTypeJsonString
            )
            DetailsScreen(
                rentalType = rentalType
            )
        }
    }
}

@Serializable
data object Home

@Serializable
data class DetailsScreen(
    val rentalTypeJsonString: String
)