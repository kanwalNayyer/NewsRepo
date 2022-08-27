package com.hisham.news.pojo.news

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Multimedia(
    var caption: String?,
    var copyright: String?,
    var format: String?,
    var height: Int?,
    var subtype: String?,
    var type: String?,
    var url: String?,
    var width: Int?
):Parcelable