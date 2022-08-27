package com.hisham.news.pojo.news

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Result(
    var `abstract`: String?,
    var byline: String?,
    var created_date: String?,
    var des_facet: List<String>?,
    var geo_facet: List<String>?,
    var item_type: String?,
    var kicker: String?,
    var material_type_facet: String?,
    var multimedia: ArrayList<Multimedia>?,
    var org_facet: List<String>?,
    var per_facet: List<String>?,
    var published_date: String?,
    var section: String?,
    var short_url: String?,
    var subsection: String?,
    var title: String?,
    var updated_date: String?,
    var uri: String?,
    var url: String?
):Parcelable