package com.corsolp.uicompose.screens.details

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Phone
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.*
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.corsolp.domain.models.RentalTypeEnum
import com.corsolp.uicompose.R
import androidx.core.net.toUri
import coil3.compose.rememberAsyncImagePainter
import com.corsolp.domain.models.Rental

@Composable
fun DetailsScreen(
    rental: Rental,
    isLoggedIn: Boolean,
    isAdmin: Boolean,
    onDeleteClick: () -> Unit
) {
    val scaffoldState = rememberScaffoldState()
    val context = LocalContext.current

    // FLAGS per mostrare snackbar
    var showLoginSnackbarForEmail by remember { mutableStateOf(false) }
    var showLoginSnackbarForPhone by remember { mutableStateOf(false) }

    // EFFETTI LATERALI sicuri
    if (showLoginSnackbarForEmail) {
        LaunchedEffect(scaffoldState.snackbarHostState) {
            scaffoldState.snackbarHostState.showSnackbar("Effettua il login per contattare")
            showLoginSnackbarForEmail = false
        }
    }

    if (showLoginSnackbarForPhone) {
        LaunchedEffect(scaffoldState.snackbarHostState) {
            scaffoldState.snackbarHostState.showSnackbar("Effettua il login per chiamare")
            showLoginSnackbarForPhone = false
        }
    }

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(title = { Text(text = "Dettaglio Annuncio") })
        },
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Card(
                    shape = RoundedCornerShape(8.dp),
                    backgroundColor = colorResource(R.color.light_gray),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Column(modifier = Modifier.weight(1f)) {
                                val categoryRes = when (rental.type) {
                                    RentalTypeEnum.ROOM      -> R.string.room
                                    RentalTypeEnum.APARTMENT -> R.string.apartment
                                    RentalTypeEnum.BED       -> R.string.bed
                                }
                                Text(
                                    text = stringResource(categoryRes),
                                    style = TextStyle(
                                        fontSize = 14.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = colorResource(R.color.gray)
                                    )
                                )
                                Spacer(Modifier.height(4.dp))
                                Text(
                                    text = rental.description,
                                    style = TextStyle(
                                        fontSize = 20.sp,
                                        fontWeight = FontWeight.SemiBold,
                                        color = colorResource(R.color.blue)
                                    )
                                )
                            }
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(4.dp))
                                    .background(colorResource(R.color.green))
                                    .padding(8.dp)
                            ) {
                                Text(
                                    text = "${rental.price} €",
                                    style = TextStyle(
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = colorResource(R.color.white)
                                    )
                                )
                            }
                        }

                        Spacer(Modifier.height(8.dp))

                        Text(
                            text = "Stanze: ${rental.rooms}    •    mq: ${rental.surface}    •    piano: ${rental.floor}",
                            style = TextStyle(
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Normal,
                                color = colorResource(R.color.dark_gray)
                            )
                        )

                        Spacer(Modifier.height(8.dp))

                        val imagePainter = rememberAsyncImagePainter(
                            model = rental.pictureUrl,
                            error = painterResource(R.drawable.placeholder),
                            placeholder = painterResource(R.drawable.placeholder)
                        )
                        Image(
                            painter = imagePainter,
                            contentDescription = "Immagine Annuncio",
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp)
                                .clip(RoundedCornerShape(8.dp))
                        )
                    }
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    // Pulsante Contatta
                    Button(
                        onClick = {
                            if (isLoggedIn) {
                                val intent = Intent(Intent.ACTION_SENDTO).apply {
                                    data = "mailto:${rental.email}".toUri()
                                }
                                context.startActivity(intent)
                            } else {
                                showLoginSnackbarForEmail = true
                            }
                        },
                        colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(R.color.blue))
                    ) {
                        Icon(Icons.Default.Email, contentDescription = null)
                        Spacer(Modifier.width(8.dp))
                        Text("Contatta")
                    }

                    // Pulsante Chiama
                    Button(
                        onClick = {
                            if (isLoggedIn) {
                                val intent = Intent(Intent.ACTION_DIAL).apply {
                                    data = "tel:${rental.phoneNumber}".toUri()
                                }
                                context.startActivity(intent)
                            } else {
                                showLoginSnackbarForPhone = true
                            }
                        },
                        colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(R.color.green))
                    ) {
                        Icon(Icons.Default.Phone, contentDescription = null)
                        Spacer(Modifier.width(8.dp))
                        Text("Chiama")
                    }

                    // Pulsante Elimina (solo admin)
                    if (isAdmin) {
                        Button(
                            onClick = onDeleteClick,
                            colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(R.color.red))
                        ) {
                            Icon(Icons.Default.Delete, contentDescription = null)
                            Spacer(Modifier.width(8.dp))
                            Text("Elimina")
                        }
                    }
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun DetailsScreenPreview() {
    DetailsScreen(
        rental = Rental(
            id = 1,
            description = "Ampio appartamento vista mare",
            pictureUrl = null,
            rooms = 3,
            surface = 80,
            floor = 2,
            services = listOf("WiFi", "Parcheggio", "Piscina"),
            price = 700.0,
            favorite = false,
            type = RentalTypeEnum.APARTMENT,
            phoneNumber = "3635521489",
            email = "siisi@titti.com"
        ),
        isLoggedIn = false,
        isAdmin = false,
        onDeleteClick = {},
    )
}
