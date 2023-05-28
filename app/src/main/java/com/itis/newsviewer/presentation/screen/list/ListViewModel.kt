package com.itis.newsviewer.presentation.screen.list

import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.itis.newsviewer.domain.news.GetNewsUseCase
import com.itis.newsviewer.domain.news.model.NewsInfo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@Immutable
data class ListViewState(
    val news: Flow<PagingData<NewsInfo>> = MutableStateFlow(PagingData.empty()),
)

sealed interface ListEvent {
    data class OnNewsItemClick(val newsInfo: NewsInfo) : ListEvent
}

sealed interface ListAction {

    data class Navigate(val id: String) : ListAction

}

class ListViewModel(
    private val getNewsUseCase: GetNewsUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(ListViewState())
    val state: StateFlow<ListViewState>
        get() = _state.asStateFlow()

    private val _action = MutableSharedFlow<ListAction?>()
    val action: SharedFlow<ListAction?>
        get() = _action.asSharedFlow()

    init {
        viewModelScope.launch {
            _state.emit(
                _state.value.copy(
                    news = getNews()
                )
            )
        }
    }

    fun event(mainEvent: ListEvent) {
        when (mainEvent) {
            is ListEvent.OnNewsItemClick -> onListItemClick(mainEvent.newsInfo)
        }
    }

    private fun onListItemClick(newsInfo: NewsInfo) {
        viewModelScope.launch {
            _action.emit(ListAction.Navigate(newsInfo.uuid))
        }
    }

    private fun getNews(): Flow<PagingData<NewsInfo>> = getNewsUseCase().cachedIn(viewModelScope)
}
