package com.example.mvvm_livedata_viewmodel.data

// Pojo-class or Model class
data class Quote(
    val quoteText: String,
    val author: String
) {
    override fun toString(): String {
        return "$quoteText -  $author"
    }
}
