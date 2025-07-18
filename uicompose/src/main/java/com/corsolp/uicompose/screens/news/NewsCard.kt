package com.corsolp.uicompose.screens.news

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.corsolp.domain.models.News
import com.corsolp.uicompose.R

@Composable
fun NewsCard(
    news: News,
    onItemClick: (News) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(dimensionResource(R.dimen.spacing_small)))
            .background(color = colorResource(R.color.light_gray))
            .clickable { onItemClick(news) }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(dimensionResource(R.dimen.spacing_medium))
        ) {
            // Titolo
            Text(
                text = news.title,
                style = TextStyle(
                    fontSize = dimensionResource(R.dimen.txt_size_large).value.sp,
                    fontWeight = FontWeight.Bold,
                    color = colorResource(R.color.blue)
                )
            )
            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.spacing_small)))
            // Data di pubblicazione
            Text(
                text = news.publishDate.slice(0..9),
                style = TextStyle(fontSize = dimensionResource(R.dimen.txt_size_normal).value.sp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NewsCardPreview() {
    NewsCard(
        news = News(
            id = 1,
            title = "Sample News Title",
            content = "This is a sample content for the news article. It provides an overview of the news topic.",
            publishDate = "2023-10-01",
            pictureUrl = "news/ciccio.jpg"
        ),
        onItemClick = {  }
    )
}