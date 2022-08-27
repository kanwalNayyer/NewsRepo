package com.hisham.news.utils.extentions



/**
 * validate empty field
 *
 * @param value => value from edittext
 */
fun String.isEmpty(): Boolean {
    return this.isNullOrEmpty()
}



