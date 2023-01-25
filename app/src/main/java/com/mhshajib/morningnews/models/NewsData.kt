package com.mhshajib.morningnews.models

data class NewsData(
    val articles: List<Article>?,
    val status: String?,
    val totalResults: Int?
)