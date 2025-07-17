package com.corsolp.uicompose.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.ui.res.colorResource
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
    onItemClick: (Rental) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(dimensionResource(R.dimen.spacing_small)))
            .background(color = colorResource(R.color.light_gray))
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
                            fontSize = dimensionResource(R.dimen.txt_size_normal).value.sp,
                            fontWeight = FontWeight.Bold,
                            color = colorResource(R.color.gray)
                        )
                    )

                    // Descrizione
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
                        .padding(dimensionResource(R.dimen.spacing_small))
                        .clip(RoundedCornerShape(dimensionResource(R.dimen.spacing_xs)))
                        .background(colorResource(R.color.green))
                ) {
                    Text(
                        text = "${rental.price}€",
                        modifier = Modifier.padding(dimensionResource(R.dimen.spacing_small)),
                        style = TextStyle(
                            fontSize = dimensionResource(R.dimen.txt_size_caption).value.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    )
                }
            }
            // Eventuali dettagli aggiuntivi
            Text(
                text = "Stanze: ${rental.rooms}, mq: ${rental.surface}, piano: ${rental.floor}",
                modifier = Modifier.padding(dimensionResource(R.dimen.spacing_small)),
                style = TextStyle(
                    fontSize = dimensionResource(R.dimen.txt_size_normal).value.sp,
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
            1,
            description = "Ampio appartamento vista mare",
            pictureUrl = null,
            rooms = 3,
            surface = 80,
            floor = 2,
            services = listOf("WiFi", "Parcheggio", "Piscina"),
            price = 700.0,
            favorite = false,
            type = RentalTypeEnum.APARTMENT,
            phoneNumber = "123456789",
            email = "dddd@saaaa.xc",
            address = "Via Roma, 123, Milano",
        ),
        onItemClick = {}
    )
}
