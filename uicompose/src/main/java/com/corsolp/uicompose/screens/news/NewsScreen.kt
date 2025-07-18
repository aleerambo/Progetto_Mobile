package com.corsolp.uicompose.screens.news

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.corsolp.domain.models.News
import com.corsolp.uicompose.R
import com.corsolp.uicompose.common.Loader

@Composable
fun NewsScreen(
    viewModel: NewsViewModel,
    showDetails: (News) -> Unit
) {
    val showLoader = viewModel.showLoader.collectAsStateWithLifecycle()
    val newsList = viewModel.newsList.collectAsStateWithLifecycle()
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        if (showLoader.value) Loader()
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.spacing_medium))
        ) {
            items(newsList.value) { news ->
                NewsCard(
                    news = news,
                    onItemClick = { showDetails(news) },
                )
            }
        }
    }
}