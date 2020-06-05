package com.ttn.sharedelementtransitionsample.recycler_view.recycler_view_to_activity

import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.ttn.sharedelementtransitionsample.R
import com.ttn.sharedelementtransitionsample.recycler_view.AnimalItem

class AnimalDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animal_detail)
        supportPostponeEnterTransition()
        val extras = intent.extras
        val animalItem: AnimalItem = extras!!.getParcelable(RecyclerViewActivity.EXTRA_ANIMAL_ITEM)!!
        val imageView =
            findViewById<View>(R.id.animal_detail_image_view) as ImageView
        val textView =
            findViewById<View>(R.id.animal_detail_text) as TextView
        textView.text = animalItem.detail
        val imageUrl = animalItem.imageUrl
        val imageTransitionName =
            extras.getString(RecyclerViewActivity.EXTRA_ANIMAL_IMAGE_TRANSITION_NAME)
        imageView.transitionName = imageTransitionName
        Picasso.get()
            .load(imageUrl)
            .noFade()
            .into(imageView, object : Callback {
                override fun onSuccess() {
                    supportStartPostponedEnterTransition()
                }

                override fun onError(e: Exception) {
                    supportStartPostponedEnterTransition()
                }
            })
    }
}