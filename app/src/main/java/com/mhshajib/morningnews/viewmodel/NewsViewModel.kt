package com.mhshajib.morningnews.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.mhshajib.morningnews.database.NewsArticle
import com.mhshajib.morningnews.database.NewsDatabase
import com.mhshajib.morningnews.global.Global
import com.mhshajib.morningnews.network.NewsApi
import com.mhshajib.morningnews.repository.NewsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewsViewModel (application: Application) : AndroidViewModel(application)
{
    //    private val _news = MutableLiveData<NewsData>()
//    private val _articles = MutableLiveData<List<Article>?>()
    private val result = mutableListOf<NewsArticle>()
    private val repository: NewsRepository
    lateinit var readNews: LiveData<List<NewsArticle>>

    init {
        val newsDao = NewsDatabase.getDatabase(application).getNewsDao()
        repository = NewsRepository(newsDao)
        readNewsFromLocal()
    }

    fun readNewsFromLocal() {
        with(repository) {
            Log.d("TAG", "readNewsFromLocal: ${Global.category}")
            readNews = when (Global.category) {
                "business" -> readNews("business")
                "entertainment" -> readNews("entertainment")
                "general" -> readNews("general")
                "health" -> readNews("health")
                "science" -> readNews("science")
                "sports" -> readNews("sports")
                else -> readNews("technology")
            }
        }
    }

    fun getNewsFromRemote() {
        Log.d("TAG", "getNewsFromRemote: call news api")
        viewModelScope.launch {
            try {
                val response = NewsApi.retrofitService.topHeadlinesNews(
                    Global.category!!
                )
                response.articles?.map {
                    result.add(
                        NewsArticle(
                            0,
                            it.title,
                            it.author,
                            it.content,
                            it.description,
                            it.publishedAt,
                            it.source?.name,
                            it.url,
                            it.urlToImage,
                            Global.category
                        )
                    )
                }
                addNews()
                Log.d("TAG", "getTopHeadlines: called ${response.articles?.size}")
            } catch (e: Exception) {
                Log.d("TAG", "$e")
            }
        }
    }

    private fun addNews() {
        Log.d("TAG", "addNews: result${result.size}")
        for (i in result) {
            viewModelScope.launch(Dispatchers.IO) {
                repository.addNews(i)
            }
        }
        result.clear()
        Log.d("TAG", "addNews: result${result.size}")
    }

    fun addBookmarkNews(newsArticle: NewsArticle) {
        Log.d("TAG", "addBookmarkNews: ${newsArticle.id}")
        viewModelScope.launch(Dispatchers.IO) {
            repository.addBookmarkNews(
                newsArticle.id, !newsArticle.isBookmark
            )
        }
    }

    fun getBookmarkNews() {
        readNews = repository.getBookmarkNews()
    }

}