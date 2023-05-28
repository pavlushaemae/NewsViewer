package com.itis.newsviewer.domain.news

import com.itis.newsviewer.domain.news.model.NewsInfo

class GetNewsByIdUseCase(
    private val repository: NewsRepository,
) {
    suspend operator fun invoke(
        id: String,
    ): NewsInfo {
        return repository.getNewsById(id)
    }
}
