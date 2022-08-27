package com.hisham.news.pojo.news

data class NewsFeedResponse(
    var copyright: String?,
    var last_updated: String?,
    var num_results: Int?,
    var results: ArrayList<Result>?,
    var section: String?,
    var status: String?
)