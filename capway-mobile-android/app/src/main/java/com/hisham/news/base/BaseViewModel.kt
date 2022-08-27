package com.hisham.news.base

import androidx.lifecycle.ViewModel
import com.hisham.news.pojo.news.NewsFeedResponse
import com.hisham.news.utils.Event


abstract class BaseViewModel : ViewModel()


data class NewsFeedDataState(
    val showProgress : Boolean,
    val data : Event<NewsFeedResponse>?,
    val error : Event<Int>?
)
