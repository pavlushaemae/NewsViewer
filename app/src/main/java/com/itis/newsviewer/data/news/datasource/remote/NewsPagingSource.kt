package com.itis.newsviewer.data.news.datasource.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.itis.newsviewer.data.news.mapper.toNewsInfoList
import com.itis.newsviewer.domain.news.model.NewsInfo
import javax.inject.Inject

class NewsPagingSource(
    private val newsApi: NewsApi,
) : PagingSource<Int, NewsInfo>() {
    override fun getRefreshKey(state: PagingState<Int, NewsInfo>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, NewsInfo> {
        return try {
            val page = params.key ?: 1
            val response = newsApi.getNewsPage(page = page)

            LoadResult.Page(
                data = response.data.toNewsInfoList(),
                prevKey = if (page == 1) null else page.minus(1),
                nextKey = if (response.data.isEmpty()) null else page.plus(1),
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}
