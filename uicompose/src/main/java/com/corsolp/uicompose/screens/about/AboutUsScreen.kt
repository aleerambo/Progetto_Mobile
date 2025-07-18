package com.corsolp.uicompose.screens.about

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.corsolp.uicompose.R

@Composable
fun AboutUsScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(dimensionResource(R.dimen.spacing_medium))
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.spacing_medium))
    ) {
        Text(
            text = stringResource(R.string.about_us),
            style = TextStyle(
                fontSize = dimensionResource(R.dimen.txt_size_title).value.sp,
                fontWeight = FontWeight.Bold,
                color = colorResource(R.color.blue)
            )
        )
        Text(
            text = stringResource(R.string.about_us_text),
            style = TextStyle(
                fontSize = dimensionResource(R.dimen.txt_size_normal).value.sp,
                color = colorResource(R.color.text_color)
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AboutUsScreenPreview() {
    AboutUsScreen()
}