package com.mhshajib.morningnews.global

import android.view.View
import com.mhshajib.morningnews.database.NewsArticle

class Global {
    companion object {
        var category: String? = null
        var newsArticle: NewsArticle? = null
        var contextView: View? = null
    }
}