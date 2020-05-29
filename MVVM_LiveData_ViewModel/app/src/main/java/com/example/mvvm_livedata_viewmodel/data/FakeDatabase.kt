package com.example.mvvm_livedata_viewmodel.data

/**
 * FakeDatabase class (Singleton class)
 */
class FakeDatabase private constructor() {

    var quotesDao = FakeQuotesDao()
        private set

    /**
     * Creating an single instance of FakeDatabase class
     */
    companion object {
        @Volatile
        private var instant: FakeDatabase? = null

        fun getInstance() =
            instant ?: synchronized(this) {
                instant ?: FakeDatabase().also { instant = it }
            }
    }
}