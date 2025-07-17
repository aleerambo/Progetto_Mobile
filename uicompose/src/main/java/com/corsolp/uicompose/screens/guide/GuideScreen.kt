package com.corsolp.uicompose.screens.guide

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.corsolp.uicompose.R

@Composable
fun GuideScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(dimensionResource(R.dimen.spacing_medium))
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.spacing_medium))
    ) {
        Text(
            text = "Guida alla ricerca dell'alloggio ideale",
            style = TextStyle(
                fontSize = dimensionResource(R.dimen.txt_size_title).value.sp,
                fontWeight = FontWeight.Bold,
                color = colorResource(R.color.blue)
            )
        )
        Text(
            text = "1. Definisci il tuo budget.\n2. Scegli la posizione ideale.\n3. Controlla i servizi inclusi.\n4. Leggi le recensioni degli altri utenti.",
            style = TextStyle(
                fontSize = dimensionResource(R.dimen.txt_size_normal).value.sp,
                color = colorResource(R.color.text_color)
            )
        )
    }
}