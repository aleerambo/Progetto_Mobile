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
import com.corsolp.domain.models.RentalType
import com.corsolp.uicompose.R
import com.corsolp.uicompose.extensions.toResId

@Composable
fun RentalCard(
    rentalType: RentalType,
    onItemClick: (RentalType) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(color = Color.LightGray)
            .clickable { onItemClick(rentalType) }
    ) {
        Column {
            Row {
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(dimensionResource(R.dimen.spacing_small))
                ) {
                    val type = stringResource(rentalType.toResId())

                    Text(
                        type,
                        style = TextStyle(
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Gray
                        )
                    )
                    Text(
                        rentalType.description,
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
                        .background(color = Color.Blue)
                ) {
                    Text(
                        rentalType.price.toString(),
                        modifier = Modifier.padding(8.dp),
                        style = TextStyle(
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    )
                }
            }
            Text(
                rentalType.description,
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

@Preview
@Composable
fun RentalCardPreview() {
    RentalCard(
        rentalType = RentalType.Apartment(
            apartmentDescription = "Nome Hotel Nuovo",
            apartmentPictureUrl = "Questa è la descrizione dell'hotel",
            apartmentPrice = 700.0,
            apartmentRooms = 3,
            apartmentSurface = 80,
            apartmentFloor = 2,
            apartmentServices = listOf("WiFi", "Parking", "Pool")
        ),
        onItemClick = {}
    )
}