package com.example.mvvm_livedata_viewmodel.ui.quotes

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mvvm_livedata_viewmodel.R
import com.example.mvvm_livedata_viewmodel.data.Quote
import com.example.mvvm_livedata_viewmodel.utilities.InjectorUtils
import kotlinx.android.synthetic.main.activity_quotes.*


class QuotesActivity : AppCompatActivity() {
    // factory method to get the instance of viewModel
    private val factory by lazy {
        InjectorUtils.provideQuotesViewModelFactor()
    }
    // getting the instance of viewModel
    private val viewModel by lazy {
        ViewModelProvider(this, factory)
            .get(QuotesViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quotes)
        initializeUi()
    }

    private fun initializeUi() {
        // setting the observer to observe the change in the viewModel class
        viewModel.getQuotes().observe(this, Observer { quotes ->
            val stringBuilder = StringBuilder()
            quotes.forEach { quote ->
                stringBuilder.append("$quote\n\n")
            }
            textView_quotes.text = stringBuilder.toString()
        })

        // button click listener to all the Quote and clear the text-fields
        button_add_quote.setOnClickListener {
            val quote = Quote(editText_quote.text.toString(), editText_author.text.toString())
            viewModel.addQuotes(quote)
            editText_quote.setText("")
            editText_author.setText("")
        }
    }
}
