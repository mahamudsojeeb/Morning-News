package com.mhshajib.morningnews.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NewsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addNews(newsArticle: NewsArticle)

    @Query("SELECT * FROM news WHERE category=:category ORDER BY id DESC")
    fun readNews(category: String): LiveData<List<NewsArticle>>

//    // Search function
//    @Query("SELECT * FROM news WHERE title LIKE '%' || :search || '%'")
//    fun searchNews(search: String?): LiveData<List<NewsArticle>>

    @Update
    suspend fun addBookmarkNews(NewsArticle: NewsArticle)

    @Query("SELECT * FROM news WHERE isBookmark=1 ORDER BY id DESC")
    fun getBookmarkNews(): LiveData<List<NewsArticle>>
}