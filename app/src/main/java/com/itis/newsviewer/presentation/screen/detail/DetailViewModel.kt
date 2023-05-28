package com.itis.newsviewer.presentation.screen.detail

import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.itis.newsviewer.domain.news.GetNewsByIdUseCase
import com.itis.newsviewer.domain.news.model.NewsInfo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@Immutable
data class DetailViewState(
    val news: NewsInfo? = null,
    val isLoading: Boolean = true,
)

sealed interface DetailEvent {
    data class Init(val id: String) : DetailEvent
    object OnUrlClick : DetailEvent
}

class DetailViewModel(
    private val getNewsByIdUseCase: GetNewsByIdUseCase,
) : ViewModel() {
    private val _state = MutableStateFlow(DetailViewState())
    val state: StateFlow<DetailViewState>
        get() = _state.asStateFlow()

    fun event(mainEvent: DetailEvent) {
        when (mainEvent) {
            DetailEvent.OnUrlClick -> Unit
            is DetailEvent.Init -> {
                init(mainEvent.id)
            }
        }
    }

    private fun init(id: String) {

        viewModelScope.launch {
            _state.emit(
                _state.value.copy(
                    news = getNewsByIdUseCase(id)
                )
            )
        }
    }

}
