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
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.Phone
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
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
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .verticalScroll(rememberScrollState())
                    .padding(dimensionResource(R.dimen.spacing_medium)),
                verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.spacing_medium))
            ) {
                Card(
                    shape = RoundedCornerShape(dimensionResource(R.dimen.spacing_small)),
                    backgroundColor = colorResource(R.color.light_gray),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(dimensionResource(R.dimen.spacing_medium))) {
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
                                        fontSize = dimensionResource(R.dimen.txt_size_normal).value.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = colorResource(R.color.gray)
                                    )
                                )
                                Spacer(Modifier.height(dimensionResource(R.dimen.spacing_xs)))
                                Text(
                                    text = rental.description,
                                    style = TextStyle(
                                        fontSize = dimensionResource(R.dimen.txt_size_large).value.sp,
                                        fontWeight = FontWeight.SemiBold,
                                        color = colorResource(R.color.blue)
                                    )
                                )
                            }
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(dimensionResource(R.dimen.spacing_xs)))
                                    .background(colorResource(R.color.green))
                                    .padding(dimensionResource(R.dimen.spacing_small))
                            ) {
                                Text(
                                    text = "${rental.price} €",
                                    style = TextStyle(
                                        fontSize = dimensionResource(R.dimen.txt_size_caption).value.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = colorResource(R.color.white)
                                    )
                                )
                            }
                        }

                        Spacer(Modifier.height(dimensionResource(R.dimen.spacing_small)))

                        Text(
                            text = "Stanze: ${rental.rooms}, mq: ${rental.surface}, piano: ${rental.floor}",
                            style = TextStyle(
                                fontSize = dimensionResource(R.dimen.txt_size_normal).value.sp,
                                fontWeight = FontWeight.Normal,
                                color = colorResource(R.color.dark_gray)
                            )
                        )

                        Spacer(Modifier.height(dimensionResource(R.dimen.spacing_small)))

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
                                .height(dimensionResource(R.dimen.img_height))
                                .clip(RoundedCornerShape(dimensionResource(R.dimen.spacing_small)))
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
                        Icon(Icons.Default.Email, contentDescription = null, tint = colorResource(R.color.white))
                        Spacer(Modifier.width(dimensionResource(R.dimen.spacing_small)))
                        Text(
                            text = "Contatta",
                            style = TextStyle(
                                color = colorResource(R.color.white),
                            )
                        )
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
                        Icon(Icons.Default.Phone, contentDescription = null, tint = colorResource(R.color.white), tint = colorResource(R.color.white))
                        Spacer(Modifier.width(dimensionResource(R.dimen.spacing_small)))
                        Text(
                            text = "Chiama",
                            style = TextStyle(
                                color = colorResource(R.color.white),
                            )
                        )
                    }

                    // Pulsante Apri in Google Maps
                    Button(
                        onClick = {
                            val gmmIntentUri = "geo:0,0?q=${Uri.encode(rental.address)}".toUri()
                            val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri).apply {
                                setPackage("com.google.android.apps.maps")
                            }
                            context.startActivity(mapIntent)
                        },
                        colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(R.color.light_gray))
                    ) {
                        Icon(
                            Icons.Default.Map,
                            contentDescription = null,
                        )
                        Spacer(Modifier.width(dimensionResource(R.dimen.spacing_small)))
                        Text(
                            text = "Mappa"
                        )
                    }

                    // Pulsante Elimina (solo admin)
                    if (isAdmin) {
                        Button(
                            onClick = onDeleteClick,
                            colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(R.color.red))
                        ) {
                            Icon(Icons.Default.Delete, contentDescription = null, tint = colorResource(R.color.white))
                            Spacer(Modifier.width(dimensionResource(R.dimen.spacing_small)))
                            Text(
                                text = "Elimina",
                                style = TextStyle(
                                    color = colorResource(R.color.white),
                                )
                            )
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
            email = "siisi@titti.com",
            address = "Via Roma, 123, Cesena",
        ),
        isLoggedIn = true,
        isAdmin = true,
        onDeleteClick = {},
    )
}
