package com.itis.newsviewer.domain.news

import com.itis.newsviewer.domain.news.model.NewsInfo
import javax.inject.Inject

class GetNewsByIdUseCase @Inject constructor(
    private val repository: NewsRepository,
) {
    suspend operator fun invoke(
        id: String,
    ): NewsInfo {
        return repository.getNewsByUuid(id)
    }
}
