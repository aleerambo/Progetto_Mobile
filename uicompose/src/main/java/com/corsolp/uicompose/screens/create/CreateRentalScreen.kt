package com.corsolp.uicompose.screens.create

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.corsolp.uicompose.R
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown

@Composable
fun CreateRentalScreen(
    viewModel: CreateRentalViewModel,
    onPostCreated: () -> Unit
) {
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
    var selectedArea by remember { mutableStateOf<Int?>(null) }
    var selectedType by remember { mutableStateOf<Int?>(null) }
    var isAreaDropdownExpanded by remember { mutableStateOf(false) }
    var isTypeDropdownExpanded by remember { mutableStateOf(false) }

    Column(modifier = Modifier
        .padding(dimensionResource(R.dimen.spacing_medium))
        .verticalScroll(rememberScrollState())
    ) {
        TextField(
            value = description,
            onValueChange = { description = it },
            label = { Text(stringResource(R.string.description)) },
            modifier = Modifier.fillMaxWidth()
        )
        TextField(
            value = address,
            onValueChange = { address = it },
            label = { Text(stringResource(R.string.address)) },
            modifier = Modifier.fillMaxWidth()
        )
        TextField(
            value = price,
            onValueChange = { price = it },
            label = { Text(stringResource(R.string.price_month)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )
        TextField(
            value = rooms,
            onValueChange = { rooms = it },
            label = { Text(stringResource(R.string.rooms)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )
        TextField(
            value = squareMeters,
            onValueChange = { squareMeters = it },
            label = { Text(stringResource(R.string.surface)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )
        TextField(
            value = floor,
            onValueChange = { floor = it },
            label = { Text(stringResource(R.string.floor)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )
        TextField(
            value = numberOfTenants,
            onValueChange = { numberOfTenants = it },
            label = { Text(stringResource(R.string.number_of_tenants)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )
        TextField(
            value = minContract,
            onValueChange = { minContract = it },
            label = { Text(stringResource(R.string.min_contract)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )
        TextField(
            value = maxContract,
            onValueChange = { maxContract = it },
            label = { Text(stringResource(R.string.max_contract)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        // Dropdown per le aree
        Box(modifier = Modifier.fillMaxWidth()) {
            OutlinedTextField(
                value = selectedArea?.let { selectedId ->
                    neighborhood.find { it.id == selectedId }?.name
                } ?: "",
                onValueChange = {},
                label = { Text(stringResource(R.string.select_neighborhood)) },
                readOnly = true,
                trailingIcon = {
                    IconButton(onClick = { isAreaDropdownExpanded = !isAreaDropdownExpanded }) {
                        Icon(
                            imageVector = Icons.Default.ArrowDropDown,
                            contentDescription = "Dropdown"
                        )
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { isAreaDropdownExpanded = !isAreaDropdownExpanded }
            )

            DropdownMenu(
                expanded = isAreaDropdownExpanded,
                onDismissRequest = { isAreaDropdownExpanded = false },
                modifier = Modifier.fillMaxWidth()
            ) {
                neighborhood.forEach { area ->
                    DropdownMenuItem(
                        onClick = {
                            selectedArea = area.id
                            isAreaDropdownExpanded = false
                            println("Selected area: ${area.name} with ID: ${area.id}")
                        }
                    ) {
                        Text(text = area.name)
                    }
                }
            }
        }

        // Dropdown per le tipologie
        Box(modifier = Modifier.fillMaxWidth()) {
            OutlinedTextField(
                value = selectedType?.let { selectedId ->
                    rentalTypes.find { it.id == selectedId }?.name
                } ?: "",
                onValueChange = {},
                label = { Text(stringResource(R.string.select_type)) },
                readOnly = true,
                trailingIcon = {
                    IconButton(onClick = { isTypeDropdownExpanded = !isTypeDropdownExpanded }) {
                        Icon(
                            imageVector = Icons.Default.ArrowDropDown,
                            contentDescription = "Dropdown"
                        )
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { isTypeDropdownExpanded = !isTypeDropdownExpanded }
            )

            DropdownMenu(
                expanded = isTypeDropdownExpanded,
                onDismissRequest = { isTypeDropdownExpanded = false },
                modifier = Modifier.fillMaxWidth()
            ) {
                rentalTypes.forEach { type ->
                    DropdownMenuItem(
                        onClick = {
                            selectedType = type.id
                            isTypeDropdownExpanded = false
                            println("Selected type: ${type.name} with ID: ${type.id}")
                        }
                    ) {
                        Text(text = type.name)
                    }
                }
            }
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
                    type = selectedType ?: 1,
                    numberOfTenants = numberOfTenants.toIntOrNull() ?: 0,
                    minContract = minContract.toIntOrNull() ?: 0,
                    maxContract = maxContract.toIntOrNull() ?: 0
                )
            }
        ) {
            Text(stringResource(R.string.create_post))
        }

        println(creationState)

        // Stato della creazione
        creationState?.let {
            if (it.isSuccess) {
                onPostCreated()
            } else {
                Text(
                    text = "Error: ${creationState!!.exceptionOrNull()?.message}",
                    color = Color.Red
                )
            }
        }
    }
}