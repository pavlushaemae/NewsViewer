package com.itis.newsviewer.domain.news

import androidx.paging.PagingData
import com.itis.newsviewer.domain.news.model.NewsInfo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetNewsUseCase @Inject constructor(
    private val repository: NewsRepository
) {
    operator fun invoke(): Flow<PagingData<NewsInfo>> {
        return repository.getNewsList()
    }
}
