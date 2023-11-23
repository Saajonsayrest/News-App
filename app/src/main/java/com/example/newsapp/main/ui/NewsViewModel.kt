package com.example.newsapp.main.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.main.models.Article
import com.example.newsapp.main.models.NewsResponse
import com.example.newsapp.main.repository.NewsRepository
import com.example.newsapp.main.util.Resource
import kotlinx.coroutines.launch

class NewsViewModel(val newsRepository: NewsRepository) : ViewModel() {

    val breakingNews: MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
     var breakingNewsPage = 1
    private var breakingNewsResponse: NewsResponse? = null


    val searchNews: MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
     var searchNewsPage = 1
    private var searchNewsResponse: NewsResponse? = null


    init {
        getBreakingNews("in")
    }

     fun getBreakingNews(countryCode: String) = viewModelScope.launch {
        breakingNews.postValue(Resource.Loading())

        val response = newsRepository.getBreakingNews(countryCode, breakingNewsPage)

        breakingNews.postValue(
            if (response.isSuccessful) {
                response.body()?.let { resultResponse ->
                    breakingNewsPage++
                    if (breakingNewsResponse == null) {
                        breakingNewsResponse = resultResponse
                    } else {
                        val oldArticles = breakingNewsResponse?.articles
                        val newArticles = resultResponse.articles
                        oldArticles?.addAll(newArticles)
                    }
                    Resource.Success(breakingNewsResponse ?: resultResponse)
                }
            } else {
                Resource.Error(response.message())
            }


        )
    }

    fun searchNews(searchQuery: String) = viewModelScope.launch {
        searchNews.postValue(Resource.Loading())
        val searchResponse = newsRepository.searchNews(searchQuery, searchNewsPage)
        searchNews.postValue(
            if (searchResponse.isSuccessful) {
                searchResponse.body()?.let { resultResponse ->
                    searchNewsPage++
                    if (searchNewsResponse == null) {
                        searchNewsResponse = resultResponse
                    } else {
                        val oldArticles = searchNewsResponse?.articles
                        val newArticles = resultResponse.articles
                        oldArticles?.addAll(newArticles)
                    }
                    Resource.Success(searchNewsResponse ?: resultResponse)
                }
            } else {
                Resource.Error(searchResponse.message())
            }
        )
    }

    fun saveArticle(article: Article) = viewModelScope.launch {
        newsRepository.upsert(article)
    }

    fun getSavedNews() = newsRepository.getSavedNews()

    fun deleteArticle(article: Article) = viewModelScope.launch {
        newsRepository.deleteNews(article)
    }


}

