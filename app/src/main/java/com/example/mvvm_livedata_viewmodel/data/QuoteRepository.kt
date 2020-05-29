package com.example.mvvm_livedata_viewmodel.data

/**
 * Repository class (Singleton class)
 */

class QuoteRepository private constructor(private val quotesDao: FakeQuotesDao){

    /**
     * Creating an single instance of QuoteRepository class
     */
    companion object {
        @Volatile
        private var instance: QuoteRepository? = null

        fun getInstance(quotesDao: FakeQuotesDao) =
            instance ?: synchronized(this) {
                instance ?: QuoteRepository(quotesDao).also { instance = it }
            }
    }

    /**
     * getting the Quote from QuoteDao class
     */
    fun addQuote(quote: Quote) {
        quotesDao.addQuote(quote)
    }

    /**
     * setting the Quote for QuoteDao class
     */
    fun getQuotes() = quotesDao.getQuotes()
}