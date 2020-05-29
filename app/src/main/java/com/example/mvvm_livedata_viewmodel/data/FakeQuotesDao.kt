package com.example.mvvm_livedata_viewmodel.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class FakeQuotesDao {
    // mutable list of Quote
    private val quoteList = mutableListOf<Quote>()
    // MutableLiveData list of Quote
    private val quotes = MutableLiveData<List<Quote>>()

    init {
        quotes.value = quoteList
    }

    /**
     * adding the Quote to MutableList and MutableLiveData
     */
    fun addQuote(quote: Quote) {
        quoteList.add(quote)
        quotes.value = quoteList
    }

    /**
     * getting the LiveData list
     */
    fun getQuotes() = quotes as LiveData<List<Quote>>
}