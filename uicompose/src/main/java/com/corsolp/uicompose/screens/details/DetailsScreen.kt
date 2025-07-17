package com.corsolp.uicompose.screens.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Phone
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.corsolp.domain.models.Rental
import com.corsolp.domain.models.RentalTypeEnum
import com.corsolp.uicompose.R

@Composable
fun DetailsScreen(
    rental: Rental,
    isLoggedIn: Boolean,
    isAdmin: Boolean,
    isOwner: Boolean,
    onContactClick: () -> Unit,
    onCallClick: () -> Unit,
    onDeleteClick: () -> Unit
) {
    Column {
        // Contenuto esistente della schermata
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(dimensionResource(R.dimen.spacing_small)))
                .background(color = colorResource(R.color.light_gray))
        ) {
            // Contenuto della card
        }

        // Pulsanti in basso
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(R.dimen.spacing_medium)),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Pulsante Contatta
            Button(
                onClick = {
                    if (isLoggedIn) onContactClick() else println("Effettua il login per contattare")
                },
                enabled = isLoggedIn,
                colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(R.color.blue))
            ) {
                Icon(Icons.Default.Email, contentDescription = "Contatta")
                Text(text = "Contatta")
            }

            // Pulsante Chiama
            Button(
                onClick = {
                    if (isLoggedIn) onCallClick() else println("Effettua il login per chiamare")
                },
                enabled = isLoggedIn,
                colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(R.color.green))
            ) {
                Icon(Icons.Default.Phone, contentDescription = "Chiama")
                Text(text = "Chiama")
            }

            // Pulsante Elimina
            if (isAdmin || isOwner) {
                Button(
                    onClick = onDeleteClick,
                    colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(R.color.red))
                ) {
                    Icon(Icons.Default.Delete, contentDescription = "Elimina")
                    Text(text = "Elimina")
                }
            }
        }
    }
}
/*
@Preview
@Composable
fun DetailsScreenPreview() {
    DetailsScreen(
        rental = Rental(
            1,
            description = "Ampio appartamento vista mare",
            pictureUrl = null,
            rooms = 3,
            surface = 80,
            floor = 2,
            services = listOf("WiFi", "Parcheggio", "Piscina"),
            price = 700.0,
            favorite = false,
            type = RentalTypeEnum.APARTMENT
        )
    )
}*/