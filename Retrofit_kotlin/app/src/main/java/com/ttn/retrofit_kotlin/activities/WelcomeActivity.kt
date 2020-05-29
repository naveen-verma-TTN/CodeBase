package com.ttn.retrofit_kotlin.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.ttn.retrofit_kotlin.R
import com.ttn.retrofit_kotlin.services.MessageService
import com.ttn.retrofit_kotlin.services.ServiceBuilder
import kotlinx.android.synthetic.main.activity_welcome.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WelcomeActivity : AppCompatActivity() {

    companion object {
        private val TAG: String = WelcomeActivity::class.java.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        /**
         * Get message from another server using Url annotation
         */
        val messageService = ServiceBuilder.buildService(MessageService::class.java)
        val requestCall = messageService.getMessages("http://10.0.2.2:7000/messages")

        requestCall.enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                when {
                    response.isSuccessful -> {
                        val messageText: String = response.body()!!
                        message.text = messageText
                    }
                    response.code() == 401 -> {
                        Toast.makeText(
                            this@WelcomeActivity,
                            "Your session has expired. Please Login again",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                    else -> {
                        // Application level failure
                        Toast.makeText(
                            this@WelcomeActivity,
                            "Failed to retrieve items",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                Log.e(TAG, t.message.toString())
            }
        })
    }

    fun getStarted(view: View) {
        val intent = Intent(this, DestinationListActivity::class.java)
        startActivity(intent)
        finish()
    }
}
