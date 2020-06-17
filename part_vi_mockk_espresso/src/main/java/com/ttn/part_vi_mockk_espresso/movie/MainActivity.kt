package com.ttn.part_vi_mockk_espresso.movie

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.request.RequestOptions
import com.ttn.part_vi_mockk_espresso.factory.MovieFragmentFactory
import com.ttn.part_vi_mockk_espresso.R
import com.ttn.part_vi_mockk_espresso.data.source.MoviesDataSource
import com.ttn.part_vi_mockk_espresso.data.source.MoviesRemoteDataSource

class MainActivity : AppCompatActivity() {

    // dependencies (typically would be injected with dagger)
    lateinit var requestOptions: RequestOptions
    lateinit var moviesDataSource: MoviesDataSource

    override fun onCreate(savedInstanceState: Bundle?) {
        initDependencies()
        supportFragmentManager.fragmentFactory = MovieFragmentFactory(
                requestOptions,
                moviesDataSource
        )
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
    }

    private fun init(){
        if(supportFragmentManager.fragments.size == 0){
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, MovieListFragment::class.java, null)
                    .commit()
        }
    }

    private fun initDependencies(){
        if(!::requestOptions.isInitialized){
            // glide
            requestOptions = RequestOptions
                    .placeholderOf(R.drawable.default_image)
                    .error(R.drawable.default_image)
        }
        if(!::moviesDataSource.isInitialized){
            // Data Source
            moviesDataSource = MoviesRemoteDataSource()
        }
    }

}








