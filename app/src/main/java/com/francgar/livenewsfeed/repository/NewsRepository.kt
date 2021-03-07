package com.francgar.livenewsfeed.repository

import com.francgar.livenewsfeed.api.Retrofitinstance
import com.francgar.livenewsfeed.db.ArticleDatabase
import com.francgar.livenewsfeed.models.Article

class NewsRepository(val db: ArticleDatabase) {

    suspend fun getBreakingNews(countryCode: String, pageNumber: Int) = Retrofitinstance.api.getBreakingNews(countryCode, pageNumber)


    suspend fun searchNews(searchQuery: String, pageNumber: Int) = Retrofitinstance.api.searchForNews(searchQuery, pageNumber)


    suspend fun insertUpdateArticle(article: Article) = db.getArticleDao().insertUpdate(article)

    fun getSavedNews() = db.getArticleDao().getAllArticles()

    suspend fun deleteArticle(article: Article) = db.getArticleDao().deleteArticle(article)
}