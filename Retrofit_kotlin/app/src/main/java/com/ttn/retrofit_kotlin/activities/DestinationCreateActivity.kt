package com.ttn.retrofit_kotlin.activities

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.ttn.retrofit_kotlin.R
import com.ttn.retrofit_kotlin.models.Destination
import com.ttn.retrofit_kotlin.services.DestinationService
import com.ttn.retrofit_kotlin.services.ServiceBuilder
import kotlinx.android.synthetic.main.activity_destiny_create.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DestinationCreateActivity : AppCompatActivity() {

    companion object {
        private val TAG: String = DestinationCreateActivity::class.java.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_destiny_create)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar as Toolbar)
        val context = this

        // Show the Up button in the action bar.
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        btn_add.setOnClickListener {
            val newDestination = Destination()
            newDestination.city = et_city.text.toString()
            newDestination.description = et_description.text.toString()
            newDestination.country = et_country.text.toString()

            /**
             * create new destination using Post annotation
             */
            val destinationService = ServiceBuilder.buildService(DestinationService::class.java)
            val requestCall = destinationService.addDestination(newDestination)

            requestCall.enqueue(object : Callback<Destination> {
                override fun onResponse(call: Call<Destination>, response: Response<Destination>) {
                    when {
                        response.isSuccessful -> {
                            val destination: Destination = response.body()!!
                            finish() // Move back to DestinationListActivity
                            Toast.makeText(
                                this@DestinationCreateActivity,
                                "Successfully added ${destination.city} to destination list.",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                        response.code() == 401 -> {
                            Toast.makeText(
                                this@DestinationCreateActivity,
                                "Your session has expired. Please Login again",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                        else -> {
                            // Application level failure
                            Toast.makeText(
                                this@DestinationCreateActivity,
                                "Failed to create items",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
                }

                override fun onFailure(call: Call<Destination>, t: Throwable) {
                    Log.e(TAG, t.message.toString())
                }
            })
        }
    }
}
