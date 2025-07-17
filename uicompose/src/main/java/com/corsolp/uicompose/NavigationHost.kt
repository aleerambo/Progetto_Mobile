package com.corsolp.uicompose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.Navigation
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.corsolp.domain.di.UseCaseProvider
import com.corsolp.domain.models.Rental
import com.corsolp.uicompose.screens.details.DetailsScreen
import com.corsolp.uicompose.screens.home.HomeScreen
import com.corsolp.uicompose.screens.home.HomeViewModelFactory
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.Icon
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource

@Composable
fun NavigationHost() {
    val navController = rememberNavController()
    val currentSection = "Homepage" // Puoi cambiare dinamicamente questa variabile in base alla sezione

    Column(modifier = Modifier.fillMaxSize()) {
        // Barra superiore con icona e nome sezione
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(colorResource(id = R.color.background_color))
                .padding(dimensionResource(id = R.dimen.spacing_medium))
        ) {
            Row {
                // Icona tonda
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "User Icon",
                    tint = colorResource(id = R.color.background_color),
                    modifier = Modifier
                        .background(colorResource(id = R.color.gray), shape = CircleShape)
                        .padding(dimensionResource(id = R.dimen.spacing_small))
                )
                // Nome della sezione
                Text(
                    text = currentSection,
                    modifier = Modifier
                        .padding(start = dimensionResource(id = R.dimen.spacing_small))
                        .align(Alignment.CenterVertically),
                    color = colorResource(id = R.color.text_color)
                )
            }
        }

        // Contenuto del NavHost
        NavHost(
            navController,
            startDestination = Home
        ) {
            composable<Home> {
                HomeScreen(
                    viewModel = viewModel(
                        factory = HomeViewModelFactory(
                            UseCaseProvider.fetchAllRentalPostsUseCase,
                        )
                    ),
                    showDetails = { rental ->
                        val rentalJsonString = Json.encodeToString(rental)
                        navController.navigate(
                            DetailsScreen(rentalJsonString)
                        )
                    }
                )
            }
            composable<DetailsScreen> { navBackStackEntry ->
                val detailsScreen = navBackStackEntry.toRoute<DetailsScreen>()
                val rental = Json.decodeFromString<Rental>(detailsScreen.rentalJsonString)

                val mainViewModel: MainViewModel = viewModel() // Ottieni il MainViewModel
                val isLoggedIn = mainViewModel.isLoggedIn.collectAsState(initial = false).value
                val currentUser = mainViewModel.currentUser.collectAsState(initial = null).value
                val isAdmin = mainViewModel.isAdmin()

                DetailsScreen(
                    rental = rental,
                    isLoggedIn = isLoggedIn,
                    isAdmin = isAdmin,
                    onDeleteClick = { /* Logica per eliminare */ }
                )
            }
        }
    }
}

@Serializable
data object Home

@Serializable
data class DetailsScreen(
    val rentalJsonString: String
)