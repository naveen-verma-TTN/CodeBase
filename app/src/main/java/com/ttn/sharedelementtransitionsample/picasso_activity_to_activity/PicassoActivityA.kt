package com.ttn.sharedelementtransitionsample.picasso_activity_to_activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.core.view.ViewCompat
import com.squareup.picasso.Picasso
import com.ttn.sharedelementtransitionsample.R
import com.ttn.sharedelementtransitionsample.picasso_activity_to_activity.PicassoActivityA

class PicassoActivityA : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.picasso_activity_a)
        val imageView =
            findViewById<View>(R.id.picasso_activity_a_imageView) as ImageView
        Picasso.get()
            .load(TIGER_PIC_URL)
            .fit()
            .centerCrop()
            .into(imageView)
        val button =
            findViewById<View>(R.id.picasso_activity_a_btn) as Button
        button.setOnClickListener {
            val intent = Intent(this@PicassoActivityA, PicassoActivityB::class.java)
            val options =
                ActivityOptionsCompat.makeSceneTransitionAnimation(
                    this@PicassoActivityA,
                    imageView,
                    ViewCompat.getTransitionName(imageView)!!
                )
            startActivity(intent, options.toBundle())
        }
    }

    companion object {
        const val TIGER_PIC_URL =
            "https://cdn.pixabay.com/photo/2016/11/29/10/07/animal-1868911_1280.jpg"
    }
}