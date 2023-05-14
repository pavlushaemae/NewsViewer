package com.itis.newsviewer.domain.news.model

data class NewsInfo(
    val uuid: String,
    val categories: List<String>,
    val description: String,
    val imageUrl: String,
    val keywords: String,
    val language: String,
    val publishedAt: String,
    val snippet: String,
    val source: String,
    val title: String,
    val url: String,
)
