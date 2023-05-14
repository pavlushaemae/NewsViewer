package com.itis.newsviewer.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.itis.newsviewer.domain.news.GetNewsUseCase
import com.itis.newsviewer.domain.news.model.NewsInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

data class MainViewState(
    val news: List<NewsInfo> = listOf(),
)

sealed interface MainEvent {
    data class onNewsItemClick(val newsInfo: NewsInfo) : MainEvent
}

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getNewsUseCase: GetNewsUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<MainViewState>(MainViewState())
    val state: StateFlow<MainViewState>
        get() = _state.asStateFlow()

    fun event(mainEvent: MainEvent) {
        when (mainEvent) {
            is MainEvent.onNewsItemClick -> {}
        }
    }
    fun getNews(): Flow<PagingData<NewsInfo>> = getNewsUseCase().cachedIn(viewModelScope)
}
