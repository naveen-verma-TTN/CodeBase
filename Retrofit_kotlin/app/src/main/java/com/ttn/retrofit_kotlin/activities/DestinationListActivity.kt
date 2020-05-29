package com.ttn.retrofit_kotlin.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.ttn.retrofit_kotlin.R
import com.ttn.retrofit_kotlin.helpers.DestinationAdapter
import com.ttn.retrofit_kotlin.models.Destination
import com.ttn.retrofit_kotlin.services.DestinationService
import com.ttn.retrofit_kotlin.services.ServiceBuilder
import kotlinx.android.synthetic.main.activity_destiny_list.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DestinationListActivity : AppCompatActivity() {

    companion object {
        private val TAG: String = DestinationListActivity::class.java.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_destiny_list)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar as Toolbar)
        toolbar.title = title

        fab.setOnClickListener {
            val intent = Intent(this@DestinationListActivity, DestinationCreateActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()

        loadDestinations()
    }

    /**
     * load the destination list <async>
     */
    private fun loadDestinations() {

        val destinationService: DestinationService =
            ServiceBuilder.buildService(DestinationService::class.java)

        // adding filters
        val filter: HashMap<String,String> = HashMap()

   /*     filter["country"] = "India"
        filter["count"] = "1"*/

        val requestCall: Call<List<Destination>> = destinationService.getDestinationList(filter)

        // enqueue() asynchronously execute call --- for synchronously use execute()
        requestCall.enqueue(object : Callback<List<Destination>> {

            override fun onResponse(
                call: Call<List<Destination>>,
                response: Response<List<Destination>>
            ) {
                when {
                    response.isSuccessful -> {
                        val destinationList: List<Destination> = response.body()!!
                        destiny_recycler_view.adapter = DestinationAdapter(destinationList)
                    }
                    response.code() == 401 -> {
                        Toast.makeText(
                            this@DestinationListActivity,
                            "Your session has expired. Please Login again",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                    else -> {
                        // Application level failure
                        Toast.makeText(
                            this@DestinationListActivity,
                            "Failed to retrieve items",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }

            override fun onFailure(call: Call<List<Destination>>, t: Throwable) {
                Log.e(TAG, t.message.toString())
            }
        })
    }
}
