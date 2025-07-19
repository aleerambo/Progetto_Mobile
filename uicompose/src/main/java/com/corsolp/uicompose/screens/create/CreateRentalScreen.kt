package com.corsolp.uicompose.screens.create

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Checkbox
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.corsolp.uicompose.R

@Composable
fun CreateRentalScreen(
    viewModel: CreateRentalViewModel,
    onImageSelected: (Uri) -> Unit,
    onPostCreated: () -> Unit
) {
    val services by viewModel.services.collectAsStateWithLifecycle()
    val neighborhood by viewModel.neighborhood.collectAsStateWithLifecycle()
    val creationState by viewModel.creationState.collectAsStateWithLifecycle()
    val rentalTypes by viewModel.rentalTypes.collectAsStateWithLifecycle()

    var description by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("") }
    var rooms by remember { mutableStateOf("") }
    var squareMeters by remember { mutableStateOf("") }
    var floor by remember { mutableStateOf("") }
    var numberOfTenants by remember { mutableStateOf("") }
    var minContract by remember { mutableStateOf("") }
    var maxContract by remember { mutableStateOf("") }
    var selectedServices by remember { mutableStateOf<List<Int>>(emptyList()) }
    var selectedArea by remember { mutableStateOf<Int?>(null) }
    var selectedType by remember { mutableStateOf<Int?>(null) }
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    var isAreaDropdownExpanded by remember { mutableStateOf(false) }
    var isTypeDropdownExpanded by remember { mutableStateOf(false) }

    Column(modifier = Modifier
        .padding(dimensionResource(R.dimen.spacing_medium))
        .verticalScroll(rememberScrollState())
    ) {
        TextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Descrizione") },
            modifier = Modifier.fillMaxWidth()
        )
        TextField(
            value = address,
            onValueChange = { address = it },
            label = { Text("Indirizzo") },
            modifier = Modifier.fillMaxWidth()
        )
        TextField(
            value = price,
            onValueChange = { price = it },
            label = { Text("Prezzo al mese") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )
        TextField(
            value = rooms,
            onValueChange = { rooms = it },
            label = { Text("Locali") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )
        TextField(
            value = squareMeters,
            onValueChange = { squareMeters = it },
            label = { Text("Metri quadri") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )
        TextField(
            value = floor,
            onValueChange = { floor = it },
            label = { Text("Piano") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )
        TextField(
            value = numberOfTenants,
            onValueChange = { numberOfTenants = it },
            label = { Text("Numero di inquilini") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )
        TextField(
            value = minContract,
            onValueChange = { minContract = it },
            label = { Text("Contratto minimo (mesi)") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )
        TextField(
            value = maxContract,
            onValueChange = { maxContract = it },
            label = { Text("Contratto massimo (mesi)") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        // Dropdown per le aree
        TextField(
            value = selectedArea?.let { neighborhood.find { it.id == selectedArea }?.name } ?: "",
            onValueChange = {},
            label = { Text("Seleziona Area") },
            modifier = Modifier
                .fillMaxWidth()
                .clickable { isAreaDropdownExpanded = true },
            readOnly = true
        )
        DropdownMenu(
            expanded = isAreaDropdownExpanded,
            onDismissRequest = { isAreaDropdownExpanded = false }
        ) {
            neighborhood.forEach { area ->
                DropdownMenuItem(onClick = {
                    selectedArea = area.id
                    isAreaDropdownExpanded = false
                }) {
                    Text(area.name)
                }
            }
        }

        // Dropdown per le tipologie
        TextField(
            value = selectedType?.let { rentalTypes.find { it.id == selectedType }?.name } ?: "",
            onValueChange = {},
            label = { Text("Seleziona Tipologia") },
            modifier = Modifier
                .fillMaxWidth()
                .clickable { isTypeDropdownExpanded = true },
            readOnly = true
        )
        DropdownMenu(
            expanded = isTypeDropdownExpanded,
            onDismissRequest = { isTypeDropdownExpanded = false }
        ) {
            rentalTypes.forEach { type ->
                DropdownMenuItem(onClick = {
                    selectedType = type.id
                    isTypeDropdownExpanded = false
                }) {
                    Text(type.name)
                }
            }
        }

        // Checklist per i servizi
        services.forEach { service ->
            Row {
                Checkbox(
                    checked = selectedServices.contains(service.id),
                    onCheckedChange = {
                        selectedServices = if (it) {
                            selectedServices + service.id
                        } else {
                            selectedServices - service.id
                        }
                    }
                )
                Text(service.name)
            }
        }

        // Pulsante per selezionare immagine
        val imagePickerLauncher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                result.data?.data?.let { uri ->
                    imageUri = uri // Aggiorna lo stato con l'URI dell'immagine selezionata
                    onImageSelected(uri)
                }
            }
        }

        Button(onClick = {
            val intent = Intent(Intent.ACTION_PICK).apply {
                type = "image/*"
            }
            imagePickerLauncher.launch(intent)
        }) {
            Text("Carica immagine")
        }

        // Pulsante per creare l'annuncio
        Button(
            onClick = {
                viewModel.createRentalPost(
                    idArea = selectedArea ?: 1,
                    price = price.toDoubleOrNull() ?: 0.0,
                    description = description,
                    rooms = rooms.toIntOrNull() ?: 0,
                    squareMeters = squareMeters.toIntOrNull() ?: 0,
                    floor = floor.toIntOrNull() ?: 0,
                    address = address,
                    selectedServices = selectedServices.joinToString(prefix = "[", postfix = "]"),
                    type = selectedType ?: 1,
                    numberOfTenants = numberOfTenants.toIntOrNull() ?: 0,
                    minContract = minContract.toIntOrNull() ?: 0,
                    maxContract = maxContract.toIntOrNull() ?: 0
                )
            }
        ) {
            Text("Crea Annuncio")
        }

        println(creationState)

        // Stato della creazione
        creationState?.let {
            if (it.isSuccess) {
                onPostCreated()
            } else {
                Text(
                    text = "Errore durante la creazione: ${creationState!!.exceptionOrNull()?.message}",
                    color = Color.Red
                )
            }
        }
    }
}