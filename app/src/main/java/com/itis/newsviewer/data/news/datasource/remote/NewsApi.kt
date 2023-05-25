package com.itis.newsviewer.data.news.datasource.remote

import com.itis.newsviewer.data.news.datasource.remote.response.NewsListResponse
import com.itis.newsviewer.data.news.datasource.remote.response.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NewsApi {
    @GET("uuid/{uuid}")
    suspend fun getNewsById(
        @Path("uuid") uuid: String,
    ): NewsResponse

    @GET("all")
    suspend fun getNewsPage(
        @Query("page") page: Int,
        @Query(value = "limit") limit: Int = 3,
    ): NewsListResponse

}
