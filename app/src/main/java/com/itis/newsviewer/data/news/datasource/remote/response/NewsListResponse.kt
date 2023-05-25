package com.itis.newsviewer.data.news.datasource.remote.response

import com.google.gson.annotations.SerializedName

data class NewsListResponse(
    @SerializedName("data")
    val data: List<Data>,
    @SerializedName("meta")
    val meta: Meta,
)
