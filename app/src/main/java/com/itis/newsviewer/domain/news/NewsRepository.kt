package com.itis.newsviewer.domain.news

import androidx.paging.PagingData
import com.itis.newsviewer.domain.news.model.NewsInfo
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    suspend fun getNewsById(uuid: String): NewsInfo
    fun getNewsList(): Flow<PagingData<NewsInfo>>
}
