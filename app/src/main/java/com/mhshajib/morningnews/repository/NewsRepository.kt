package com.mhshajib.morningnews.repository

import com.mhshajib.morningnews.database.NewsArticle
import com.mhshajib.morningnews.database.NewsDao

class NewsRepository(private val newsDao: NewsDao){

    fun readNews(category: String) = newsDao.readNews(category)

    suspend fun addNews(newsArticle: NewsArticle) {
        newsDao.addNews(newsArticle)
    }

    suspend fun addBookmarkNews(newsArticle: NewsArticle) {
        newsDao.addBookmarkNews(newsArticle)
    }

    fun getBookmarkNews() = newsDao.getBookmarkNews()
    fun addBookmarkNews(newsArticle: Int, b: Boolean) {
    }
}