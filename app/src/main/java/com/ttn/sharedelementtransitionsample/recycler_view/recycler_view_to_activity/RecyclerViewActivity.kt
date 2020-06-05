package com.ttn.sharedelementtransitionsample.recycler_view.recycler_view_to_activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ttn.sharedelementtransitionsample.R
import com.ttn.sharedelementtransitionsample.recycler_view.AnimalGalleryAdapter
import com.ttn.sharedelementtransitionsample.recycler_view.AnimalItem
import com.ttn.sharedelementtransitionsample.recycler_view.AnimalItemClickListener
import com.ttn.sharedelementtransitionsample.recycler_view.Utils

class RecyclerViewActivity : AppCompatActivity(), AnimalItemClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view)
        val animalGalleryAdapter = AnimalGalleryAdapter(
            Utils.generateAnimalItems(this),
            this
        )
        val recyclerView =
            findViewById<View>(R.id.recycler_view) as RecyclerView
        val gridLayoutManager = GridLayoutManager(this, 2)
        recyclerView.layoutManager = gridLayoutManager
        recyclerView.adapter = animalGalleryAdapter
    }

    override fun onAnimalItemClick(pos: Int, animalItem: AnimalItem, shareImageView: View) {
        val intent = Intent(this, AnimalDetailActivity::class.java)
        intent.putExtra(EXTRA_ANIMAL_ITEM, animalItem)
        intent.putExtra(
            EXTRA_ANIMAL_IMAGE_TRANSITION_NAME,
            ViewCompat.getTransitionName(shareImageView)
        )
        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
            this,
            shareImageView,
            ViewCompat.getTransitionName(shareImageView)!!
        )
        startActivity(intent, options.toBundle())
    }

    companion object {
        const val EXTRA_ANIMAL_ITEM = "animal_image_url"
        const val EXTRA_ANIMAL_IMAGE_TRANSITION_NAME = "animal_image_transition_name"
    }
}