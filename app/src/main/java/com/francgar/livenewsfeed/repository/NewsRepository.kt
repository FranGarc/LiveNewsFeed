package com.francgar.livenewsfeed.repository

import com.francgar.livenewsfeed.api.Retrofitinstance
import com.francgar.livenewsfeed.db.ArticleDatabase

class NewsRepository(val db: ArticleDatabase) {

    suspend fun getBreakingNews(countryCode: String, pageNumber: Int) = Retrofitinstance.api.getBreakingNews(countryCode, pageNumber)


    suspend fun searchNews(searchQuery: String, pageNumber: Int) = Retrofitinstance.api.searchForNews(searchQuery, pageNumber)
}