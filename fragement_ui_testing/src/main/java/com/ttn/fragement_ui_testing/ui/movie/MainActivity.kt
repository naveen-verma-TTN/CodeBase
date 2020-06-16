package com.ttn.fragement_ui_testing.ui.movie

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ttn.fragement_ui_testing.R
import com.ttn.fragement_ui_testing.factory.MovieFragmentFactory

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        supportFragmentManager.fragmentFactory = MovieFragmentFactory()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
    }

    private fun init() {
        if (supportFragmentManager.fragments.size == 0) {
            val movieId = 1
            val bundle = Bundle()
            bundle.putInt("movie_id", movieId)
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, MovieDetailFragment::class.java, bundle)
                    .commit()
        }
    }

}







