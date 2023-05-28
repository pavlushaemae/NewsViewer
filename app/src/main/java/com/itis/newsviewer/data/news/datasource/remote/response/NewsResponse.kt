package com.itis.newsviewer.data.news.datasource.remote.response

import com.google.gson.annotations.SerializedName

data class NewsResponse(
    @SerializedName("categories")
    val categories: List<String>,
    @SerializedName("description")
    val description: String,
    @SerializedName("image_url")
    val imageUrl: String,
    @SerializedName("keywords")
    val keywords: String,
    @SerializedName("language")
    val language: String,
    @SerializedName("published_at")
    val publishedAt: String,
    @SerializedName("snippet")
    val snippet: String,
    @SerializedName("source")
    val source: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("url")
    val url: String,
    @SerializedName("uuid")
    val uuid: String,
)
