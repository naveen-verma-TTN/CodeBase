package com.ttn.retrofit_kotlin.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.ttn.retrofit_kotlin.R
import com.ttn.retrofit_kotlin.models.Destination
import com.ttn.retrofit_kotlin.services.DestinationService
import com.ttn.retrofit_kotlin.services.ServiceBuilder
import kotlinx.android.synthetic.main.activity_destiny_detail.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class DestinationDetailActivity : AppCompatActivity() {

    companion object {
        private val TAG: String = DestinationDetailActivity::class.java.simpleName
        const val ARG_ITEM_ID = "item_id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_destiny_detail)

        val toolbar = findViewById<Toolbar>(R.id.detail_toolbar)
        setSupportActionBar(toolbar)
        // Show the Up button in the action bar.
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val bundle: Bundle? = intent.extras

        if (bundle?.containsKey(ARG_ITEM_ID)!!) {

            val id = intent.getIntExtra(ARG_ITEM_ID, 0)

            loadDetails(id)

            initUpdateButton(id)

            initDeleteButton(id)
        }
    }

    /**
     * load the destination using Id
     */
    private fun loadDetails(id: Int) {
        val destinationService = ServiceBuilder.buildService(DestinationService::class.java)
        val call: Call<Destination> = destinationService.getDestination(id)
        call.enqueue(object : Callback<Destination> {
            override fun onResponse(call: Call<Destination>, response: Response<Destination>) {
                when {
                    response.isSuccessful -> {
                        val destination: Destination = response.body()!!
                        destination.let {
                            et_city.setText(it.city)
                            et_description.setText(it.description)
                            et_country.setText(it.country)

                            collapsing_toolbar.title = it.city
                        }
                    }
                    response.code() == 401 -> {
                        Toast.makeText(
                            this@DestinationDetailActivity,
                            "Your session has expired. Please Login again",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                    else -> {
                        // Application level failure
                        Toast.makeText(
                            this@DestinationDetailActivity,
                            "Failed to retrieve items",
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

    /**
     * fun to update the destination using Put and FormUrlEncoded
     */
    private fun initUpdateButton(id: Int) {

        btn_update.setOnClickListener {

            val city = et_city.text.toString()
            val description = et_description.text.toString()
            val country = et_country.text.toString()

            val destinationService = ServiceBuilder.buildService(DestinationService::class.java)
            val callRequest = destinationService.updateDestination(
                id,
                city,
                description,
                country
            )

            callRequest.enqueue(object : Callback<Destination> {
                override fun onResponse(call: Call<Destination>, response: Response<Destination>) {
                    when {
                        response.isSuccessful -> {
                            val destination: Destination = response.body()!!
                            finish()
                            Toast.makeText(
                                this@DestinationDetailActivity,
                                "Successfully updated ${destination.city} in destination list.",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                        response.code() == 401 -> {
                            Toast.makeText(
                                this@DestinationDetailActivity,
                                "Your session has expired. Please Login again",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                        else -> {
                            // Application level failure
                            Toast.makeText(
                                this@DestinationDetailActivity,
                                "Failed to create items",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
                }

                override fun onFailure(
                    call: Call<Destination>
                    , t: Throwable
                ) {
                    Log.e(TAG, t.message.toString())
                }
            })
        }
    }

    /**
     * Fun to delete destination
     */
    private fun initDeleteButton(id: Int) {
        btn_delete.setOnClickListener {
            val destinationService = ServiceBuilder.buildService(DestinationService::class.java)
            val callRequest = destinationService.deleteDestination(id)

            callRequest.enqueue(object : Callback<Unit> {
                override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                    when {
                        response.isSuccessful -> {
                            finish()
                            Toast.makeText(
                                this@DestinationDetailActivity,
                                "Successfully deleted destination.",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                        response.code() == 401 -> {
                            Toast.makeText(
                                this@DestinationDetailActivity,
                                "Your session has expired. Please Login again",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                        else -> {
                            // Application level failure
                            Toast.makeText(
                                this@DestinationDetailActivity,
                                "Failed to delete items",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
                }

                override fun onFailure(
                    call: Call<Unit>
                    , t: Throwable
                ) {
                    Log.e(TAG, t.message.toString())
                }
            })
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == android.R.id.home) {
            navigateUpTo(Intent(this, DestinationListActivity::class.java))
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
