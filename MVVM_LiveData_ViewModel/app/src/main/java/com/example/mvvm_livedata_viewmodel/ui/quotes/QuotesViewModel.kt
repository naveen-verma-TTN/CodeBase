package com.example.mvvm_livedata_viewmodel.ui.quotes

import androidx.lifecycle.ViewModel
import com.example.mvvm_livedata_viewmodel.data.Quote
import com.example.mvvm_livedata_viewmodel.data.QuoteRepository

/**
 * ViewModel class (Singleton class)
 */
class QuotesViewModel(private val quoteRepository: QuoteRepository) : ViewModel() {

    /**
     * getting the Quote from QuoteRepository class
     */
    fun getQuotes() = quoteRepository.getQuotes()

    /**
     * setting the Quote for QuoteRepository class
     */
    fun addQuotes(quote: Quote) = quoteRepository.addQuote(quote)

}