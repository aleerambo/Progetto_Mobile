package com.corsolp.uicompose.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.corsolp.domain.models.Rental
import com.corsolp.domain.models.RentalTypeEnum
import com.corsolp.uicompose.R

@Composable
fun RentalCard(
    rental: Rental,
    onItemClick: (Rental) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(Color.LightGray)
            .clickable { onItemClick(rental) }
    ) {
        Column {
            Row {
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(dimensionResource(R.dimen.spacing_small))
                ) {
                    // Categoria:
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
                            color = Color.Gray
                        )
                    )

                    // Descrizione
                    Text(
                        text = rental.description,
                        style = TextStyle(
                            fontSize = 20.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.DarkGray
                        )
                    )
                }
                Box(
                    modifier = Modifier
                        .padding(8.dp)
                        .clip(RoundedCornerShape(4.dp))
                        .background(Color.Blue)
                ) {
                    Text(
                        text = "${rental.price}€",
                        modifier = Modifier.padding(8.dp),
                        style = TextStyle(
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    )
                }
            }
            // Eventuali dettagli aggiuntivi
            Text(
                text = "Stanze: ${rental.rooms}, mq: ${rental.surface}, piano: ${rental.floor}",
                modifier = Modifier.padding(8.dp),
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.DarkGray
                )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RentalCardPreview() {
    RentalCard(
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
            type = RentalTypeEnum.APARTMENT
        ),
        onItemClick = {}
    )
}
