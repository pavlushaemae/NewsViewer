package com.itis.newsviewer.domain.news.model

data class NewsInfo(
    val uuid: String,
    val description: String,
    val imageUrl: String,
    val snippet: String,
    val source: String,
    val title: String,
    val url: String,
)
