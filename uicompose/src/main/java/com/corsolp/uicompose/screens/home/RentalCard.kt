package com.corsolp.ui.compose.screens.home

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
                        rentalType.name,
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
                        rentalType.score.toString(),
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
        rentalType = RentalType.Hotel(
            hotelName = "Nome Hotel Nuovo",
            hotelDescription = "Questa è la descrizione dell'hotel",
            hotelPictureUrl = "",
            hotelScore = 7.3,
        ),
        onItemClick = {}
    )
}