package com.hisham.news.data.api

import com.hisham.news.data.api.ApiKeys.API_KEY_CONST
import com.hisham.news.data.api.ApiKeys.REQUEST_CONTENT
import com.hisham.news.pojo.news.NewsFeedResponse
import retrofit2.http.*


interface NewsApiServices {

    @GET(value = REQUEST_CONTENT)
    suspend fun getNewsContent(@Query(value = API_KEY_CONST) apiKey: String?) : NewsFeedResponse
}