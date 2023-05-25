package com.itis.newsviewer.ui.screen.list

import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.itis.newsviewer.domain.news.GetNewsUseCase
import com.itis.newsviewer.domain.news.model.NewsInfo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@Immutable
data class MainViewState(
    val news: Flow<PagingData<NewsInfo>> = MutableStateFlow(PagingData.empty()),
)

sealed interface MainEvent {
    data class onNewsItemClick(val newsInfo: NewsInfo) : MainEvent
}

class MainViewModel(
    private val getNewsUseCase: GetNewsUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow<MainViewState>(MainViewState())
    val state: StateFlow<MainViewState>
        get() = _state.asStateFlow()

    init {
        viewModelScope.launch {
            _state.emit(
                _state.value.copy(
                    news = getNews()
                )
            )
        }
    }

    fun event(mainEvent: MainEvent) {
        when (mainEvent) {
            is MainEvent.onNewsItemClick -> {}
        }
    }


    fun getNews(): Flow<PagingData<NewsInfo>> = getNewsUseCase().cachedIn(viewModelScope)
}
