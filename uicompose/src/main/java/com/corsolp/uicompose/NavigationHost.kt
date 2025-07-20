package com.corsolp.uicompose

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.DrawerValue
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.corsolp.domain.di.UseCaseProvider
import com.corsolp.domain.models.Rental
import com.corsolp.uicompose.screens.create.CreateRentalScreen
import com.corsolp.uicompose.screens.create.CreateRentalViewModelFactory
import com.corsolp.uicompose.screens.details.DetailsScreen
import com.corsolp.uicompose.screens.home.HomeScreen
import com.corsolp.uicompose.screens.home.HomeViewModelFactory
import kotlinx.serialization.json.Json
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.Icon
import androidx.compose.material.ModalDrawer
import androidx.compose.material.rememberDrawerState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.corsolp.domain.models.News
import com.corsolp.uicompose.screens.about.AboutUsScreen
import com.corsolp.uicompose.screens.auth.AuthViewModelFactory
import com.corsolp.uicompose.screens.auth.LoginScreen
import com.corsolp.uicompose.screens.auth.RegisterScreen
import com.corsolp.uicompose.screens.contact.ContactScreen
import com.corsolp.uicompose.screens.guide.GuideScreen
import com.corsolp.uicompose.screens.details.NewsDetailsScreen
import com.corsolp.uicompose.screens.news.NewsScreen
import com.corsolp.uicompose.screens.news.NewsViewModelFactory
import kotlinx.coroutines.launch

@Composable
fun NavigationHost() {
    val navController = rememberNavController()
    val menu = "Menu"
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val mainViewModel: MainViewModel = viewModel(
        factory = MainViewModelFactory(
            UseCaseProvider.deleteRentalPostUseCase,
            UseCaseProvider.logoutUseCase
        )
    )
    val isLoggedIn = mainViewModel.isLoggedIn.collectAsState(initial = false).value

    ModalDrawer(
        drawerState = drawerState,
        drawerContent = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(colorResource(id = R.color.light_gray))
                    .padding(dimensionResource(id = R.dimen.spacing_medium))
            ) {
                Text(
                    text = "Menu",
                    style = TextStyle(
                        fontSize = dimensionResource(id = R.dimen.txt_size_title).value.sp,
                        fontWeight = FontWeight.Bold,
                        color = colorResource(id = R.color.blue)
                    )
                )
                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacing_medium)))
                // Pulsanti del menu
                Button(onClick = {
                    scope.launch {
                        drawerState.close()
                    }
                    navController.navigate(Routes.Home)
                }) {
                    Text(stringResource(R.string.homepage_menu))
                }
                Button(onClick = {
                    scope.launch {
                        drawerState.close()
                    }
                    navController.navigate(Routes.News)
                }) {
                    Text(stringResource(R.string.news_menu))
                }
                Button(onClick = {
                    scope.launch {
                        drawerState.close()
                    }
                    navController.navigate(Routes.Guide)
                }) {
                    Text(stringResource(R.string.guide_menu))
                }
                Button(onClick = {
                    scope.launch {
                        drawerState.close()
                    }
                    navController.navigate(Routes.AboutUs)
                }) {
                    Text(stringResource(R.string.about_us))
                }
                Button(onClick = {
                    scope.launch {
                        drawerState.close()
                    }
                    navController.navigate(Routes.Contact)
                }) {
                    Text(stringResource(R.string.contact_us))
                }
                if (isLoggedIn) {
                    Button(
                        onClick = {
                            scope.launch {
                                drawerState.close()
                            }
                            navController.navigate(Routes.CreateRental)
                        },
                    ) {
                        Text(stringResource(R.string.create_post))
                    }
                }
                Button(
                    onClick = {
                        scope.launch {
                            drawerState.close()
                        }
                        if (isLoggedIn) {
                            mainViewModel.logout() // Effettua il logout
                            navController.navigate(Routes.Home) { // Naviga alla homepage
                                popUpTo(Routes.Home) { inclusive = true } // Rimuove le schermate precedenti dallo stack
                            }
                        } else {
                            navController.navigate(Routes.Login) // Naviga alla schermata di login
                        }
                    },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = if (isLoggedIn) {
                            colorResource(id = R.color.red) // Colore rosso per il logout
                        } else {
                            colorResource(id = R.color.blue) // Colore blu per il login
                        }
                    )
                ) {
                    Text(
                        text = if (isLoggedIn) {
                            "Logout"
                        } else {
                            "Login"
                        },
                        color = colorResource(id = R.color.white)
                    )
                }
            }
        }
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            // Barra superiore con icona
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(colorResource(id = R.color.background_color))
                    .padding(dimensionResource(id = R.dimen.spacing_medium))
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    // Icona tonda come pulsante
                    val isLoggedIn = mainViewModel.isLoggedIn.collectAsState(initial = false).value
                    val isAdmin = mainViewModel.isAdmin()
                    val userIconColor = when {
                        !isLoggedIn -> colorResource(id = R.color.gray)
                        isAdmin     -> colorResource(id = R.color.red)
                        else        -> colorResource(id = R.color.blue)
                    }

                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = "User Icon",
                        tint = userIconColor,
                        modifier = Modifier
                            .background(colorResource(id = R.color.light_gray), shape = CircleShape)
                            .padding(dimensionResource(id = R.dimen.spacing_small))
                            .clickable {
                                scope.launch { drawerState.open() }
                            }
                    )
                    Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.spacing_small)))
                    Text(
                        text = menu,
                        color = colorResource(id = R.color.text_color),
                        style = TextStyle(fontWeight = FontWeight.Bold)
                    )
                }
            }

            // Contenuto del NavHost
            NavHost(
                navController,
                startDestination = Routes.Home
            ) {
                composable(Routes.Home) {
                    HomeScreen(
                        viewModel = viewModel(
                            factory = HomeViewModelFactory(
                                UseCaseProvider.fetchAllRentalPostsUseCase,
                            )
                        ),
                        showDetails = { rental ->
                            val rentalJsonString = Json.encodeToString(rental)
                            navController.navigate("${Routes.DetailsScreen}/$rentalJsonString")
                        }
                    )
                }
                composable("${Routes.DetailsScreen}/{rentalJsonString}") { navBackStackEntry ->
                    val rentalJsonString = navBackStackEntry.arguments?.getString("rentalJsonString")
                    val rental = rentalJsonString?.let { Json.decodeFromString<Rental>(it) }

                    if (rental != null) {
                        DetailsScreen(
                            rental = rental,
                            mainViewModel = mainViewModel,
                            onDeleteClick = { navController.navigate(Routes.Home) },
                        )
                    }
                }
                composable(Routes.News) {
                    NewsScreen(
                        viewModel = viewModel(
                            factory = NewsViewModelFactory(
                                UseCaseProvider.fetchNewsUseCase,
                            )
                        ),
                        showDetails = { news ->
                            val newsJsonString = Json.encodeToString(news)
                            navController.navigate("${Routes.NewsDetails}/$newsJsonString")
                        }
                    )
                }
                composable("${Routes.NewsDetails}/{newsJsonString}") { navBackStackEntry ->
                    val newsJsonString = navBackStackEntry.arguments?.getString("newsJsonString")
                    val news = newsJsonString?.let { Json.decodeFromString<News>(it) }
                    if (news != null) {
                        NewsDetailsScreen(news = news)
                    }
                }
                composable(Routes.Guide) { GuideScreen() }
                composable(Routes.AboutUs) { AboutUsScreen() }
                composable(Routes.Contact) { ContactScreen() }
                composable(Routes.CreateRental) {
                    CreateRentalScreen(
                        viewModel = viewModel(
                            factory = CreateRentalViewModelFactory(
                                UseCaseProvider.createRentalPostUseCase,
                                UseCaseProvider.fetchNeighborhoodListUseCase,
                                UseCaseProvider.fetchRentalTypeListUseCase
                            )
                        ),
                        onPostCreated = {
                            navController.navigate(Routes.Home) { popUpTo(Routes.Home) { inclusive = true } }
                        }
                    )
                }
                composable(Routes.Login) {
                    LoginScreen(
                        viewModel = viewModel(
                            factory = AuthViewModelFactory(
                                UseCaseProvider.loginUseCase,
                                UseCaseProvider.registerUseCase
                            )
                        ),
                        mainViewModel = mainViewModel,
                        onLoginSuccess = {
                            navController.navigate(Routes.Home) {
                                popUpTo(Routes.Login) { inclusive = true }
                            }
                        },
                        onNavigateToRegister = {
                            navController.navigate(Routes.Register) // Naviga alla schermata di registrazione
                        }
                    )
                }
                composable(Routes.Register) {
                    RegisterScreen(
                        mainViewModel = viewModel(
                            factory = MainViewModelFactory(
                                UseCaseProvider.deleteRentalPostUseCase,
                                UseCaseProvider.logoutUseCase
                            )
                        ),
                        authViewModel = viewModel(
                            factory = AuthViewModelFactory(
                                UseCaseProvider.loginUseCase,
                                UseCaseProvider.registerUseCase
                            )
                        ),
                        onRegisterSuccess = {
                            navController.navigate(Routes.Login) {
                                popUpTo(Routes.Register) { inclusive = true }
                            }
                        },
                        onBackToLogin = {
                            navController.navigate(Routes.Login) {
                                popUpTo(Routes.Register) { inclusive = true }
                            }
                        }
                    )
                }
            }
        }
    }
}

object Routes {
    const val Home = "home"
    const val DetailsScreen = "details_screen"
    const val News = "news"
    const val NewsDetails = "news_details"
    const val Guide = "guide"
    const val AboutUs = "about_us"
    const val Contact = "contact"
    const val Login = "login"
    const val Register = "register"
    const val CreateRental = "create_rental"
}