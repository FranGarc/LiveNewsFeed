package com.francgar.livenewsfeed.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.francgar.livenewsfeed.models.NewsResponse
import com.francgar.livenewsfeed.repository.NewsRepository
import com.francgar.livenewsfeed.util.CLog
import com.francgar.livenewsfeed.util.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class NewsViewModel(val newsRepository: NewsRepository) : ViewModel() {
    val breakingNews: MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    var breakingNewsPage = 1

    val countryCode = "us"

    init {
        getBreakingNews(countryCode)
    }


    fun getBreakingNews(countryCode: String) = viewModelScope.launch {
        CLog.v("NewsViewModel.getBreakingNews(countryCode: $countryCode)")
        breakingNews.postValue(Resource.Loading())
        val response = newsRepository.getBreakingNews(countryCode, breakingNewsPage)
        CLog.v("NewsViewModel.getBreakingNews()response: $response")
        breakingNews.postValue(handleBreakingNewsResponse(response))

    }


    private fun handleBreakingNewsResponse(response: Response<NewsResponse>): Resource<NewsResponse> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }
}