package com.example.mvvm_livedata_viewmodel.utilities

import com.example.mvvm_livedata_viewmodel.data.FakeDatabase
import com.example.mvvm_livedata_viewmodel.data.QuoteRepository
import com.example.mvvm_livedata_viewmodel.ui.quotes.QuotesViewModelFactory

/**
 * Object class to create instance of QuotesViewModelFactory
 */
object InjectorUtils {

    fun provideQuotesViewModelFactor(): QuotesViewModelFactory {
        val quoteRepository = QuoteRepository.getInstance(FakeDatabase.getInstance().quotesDao)
        return QuotesViewModelFactory(quoteRepository)
    }
}