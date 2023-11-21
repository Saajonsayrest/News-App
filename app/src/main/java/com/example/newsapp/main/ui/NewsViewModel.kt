package com.example.newsapp.main.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.main.models.NewsResponse
import com.example.newsapp.main.repository.NewsRepository
import com.example.newsapp.main.util.Resource
import kotlinx.coroutines.launch

class NewsViewModel(val newsRepository: NewsRepository) : ViewModel() {

    val breakingNews: MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    private val breakingNewsPage = 1

    val searchNews: MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    private val searchNewsPage = 1

    init {
        getBreakingNews("in")
    }

    private fun getBreakingNews(countryCode: String) = viewModelScope.launch {
        breakingNews.postValue(Resource.Loading())

        val response = newsRepository.getBreakingNews(countryCode, breakingNewsPage)

        breakingNews.postValue(
            if (response.isSuccessful && response.body() != null) {
                Resource.Success(response.body()!!)
            } else {
                Resource.Error(response.message() ?: "Unknown error")
            }
        )
    }

    fun searchNews(searchQuery: String) = viewModelScope.launch {
        searchNews.postValue(Resource.Loading())
        val searchResponse = newsRepository.searchNews(searchQuery, searchNewsPage)
        searchNews.postValue(
            if (searchResponse.isSuccessful && searchResponse.body() != null) {
                Resource.Success(searchResponse.body()!!)
            } else {
                Resource.Error(searchResponse.message() ?: "Unknown Error")
            }
        )
    }


}

