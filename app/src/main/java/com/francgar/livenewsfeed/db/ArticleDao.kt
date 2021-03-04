package com.francgar.livenewsfeed.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.francgar.livenewsfeed.models.Article

@Dao
interface ArticleDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUpdate(article: Article): Long

    @Query("SELECT * FROM articles")
    fun getAllArticles(): LiveData<List<Article>>

    @Delete
    suspend fun deleteArticle(article: Article)
}