package com.ttn.recyclerview.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.ttn.recyclerview.R
import com.ttn.recyclerview.model.MediaObject
import com.ttn.recyclerview.model.repo.Resources
import com.ttn.recyclerview.utils.VerticalSpacingItemDecorator
import com.ttn.recyclerview.view.adapters.VideoPlayerRecyclerAdapter
import com.ttn.recyclerview.view.adapters.VideoPlayerRecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var mRecyclerView: VideoPlayerRecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mRecyclerView = findViewById(R.id.recycler_view)

        initRecyclerView()
    }

    private fun initRecyclerView() {
        val layoutManager = LinearLayoutManager(this)
        mRecyclerView.layoutManager = layoutManager

        val itemDecorator = VerticalSpacingItemDecorator(10)
        mRecyclerView.addItemDecoration(itemDecorator)

        val mediaObjects: ArrayList<MediaObject> = ArrayList((Resources.MEDIA_OBJECTS.toList()))
        mRecyclerView.setMediaObjects(mediaObjects)

        val adapter = VideoPlayerRecyclerAdapter(initGlide())
        adapter.setList(mediaObjects)
        mRecyclerView.adapter = adapter
    }

    private fun initGlide(): RequestManager {
        val options: RequestOptions = RequestOptions()
            .placeholder(R.drawable.white_background)
            .error(R.drawable.white_background)

        return Glide.with(this)
            .setDefaultRequestOptions(options)
    }


    @Override
    override fun onDestroy() {
        mRecyclerView.releasePlayer()
        super.onDestroy()
    }
}