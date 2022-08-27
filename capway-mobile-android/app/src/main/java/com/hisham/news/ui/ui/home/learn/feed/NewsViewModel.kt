package com.hisham.news.ui.ui.home.learn.feed

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope

import com.hisham.news.R
import com.hisham.news.base.BaseViewModel
import com.hisham.news.base.NewsFeedDataState
import com.hisham.news.data.api.ApiKeys
import com.hisham.news.data.api.NewsApiServices
import com.hisham.news.pojo.news.NewsFeedResponse
import com.hisham.news.utils.Event
import kotlinx.coroutines.launch


class NewsViewModel(val serviceUtil: NewsApiServices) :BaseViewModel(){

    private val _uiState = MutableLiveData<NewsFeedDataState>()  // 2
    val uiState: LiveData<NewsFeedDataState> = _uiState

    fun getNewsFeed()
    {
        viewModelScope.launch {   // 4
            runCatching {  //  5
                emitUiState(showProgress = true)
                serviceUtil.getNewsContent(ApiKeys.API_KEY)
            }.onSuccess {
                emitUiState(media = Event(it))
            }.onFailure {
                emitUiState(error = Event(R.string.internet_failure_error))
            }
        }
    }

    private fun emitUiState(
        showProgress : Boolean = false,
        media: Event<NewsFeedResponse>? = null,
        error : Event<Int>? = null
    ) {
        val dataState = NewsFeedDataState(showProgress, media, error)
        _uiState.value = dataState
    }

}