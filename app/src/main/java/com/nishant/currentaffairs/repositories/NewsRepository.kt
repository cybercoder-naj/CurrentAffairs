package com.nishant.currentaffairs.repositories

import com.nishant.currentaffairs.api.NewsApi
import com.nishant.currentaffairs.db.ArticleDatabase
import com.nishant.currentaffairs.models.Article
import javax.inject.Inject

class NewsRepository @Inject constructor(
    val db: ArticleDatabase,
    private val api: NewsApi
) {
    suspend fun getBreakingNews(countryCode: String, pageNumber: Int) =
        api.getBreakingNews(countryCode, pageNumber)

    suspend fun searchNews(searchQuery: String, pageNumber: Int) =
        api.searchForNews(searchQuery, pageNumber)

    suspend fun upsert(article: Article) =
        db.articleDao().upsert(article)

    fun getSavedNews() = db.articleDao().getAllArticles()

    suspend fun deleteArticle(article: Article) =
        db.articleDao().deleteArticle(article)
}