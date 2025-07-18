package com.corsolp.uicompose.screens.news

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.corsolp.domain.models.News
import com.corsolp.domain.usecases.FetchNewsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.dropWhile
import kotlinx.coroutines.launch

class NewsViewModel(
    private val fetchNewsUseCase: FetchNewsUseCase
): ViewModel() {
    private val _newsList = MutableStateFlow<List<News>>(listOf())
    val newsList: StateFlow<List<News>> = _newsList

    private val _showLoader = MutableStateFlow(false)
    val showLoader: StateFlow<Boolean> = _showLoader

    init {
        fetchNews()
    }

    private fun fetchNews() {
        viewModelScope.launch {
            _showLoader.emit(true)
            fetchNewsUseCase().dropWhile {
                it.isEmpty()
            }.collect { news ->
                _newsList.emit(news)
                _showLoader.emit(false)
            }
        }
    }
}