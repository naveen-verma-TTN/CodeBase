package com.ttn.sharedelementtransitionsample.glide_activity_to_activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.core.view.ViewCompat
import com.bumptech.glide.Glide
import com.ttn.sharedelementtransitionsample.R
import com.ttn.sharedelementtransitionsample.glide_activity_to_activity.GlideActivityA

class GlideActivityA : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.glide_activity_a)
        val imageView =
            findViewById<View>(R.id.glide_activity_a_imageView) as ImageView
        Glide.with(this)
            .load(FOX_PIC_URL)
            .centerCrop()
            .into(imageView)
        val button =
            findViewById<View>(R.id.glide_activity_a_btn) as Button
        button.setOnClickListener {
            val intent = Intent(this@GlideActivityA, GlideActivityB::class.java)
            val options =
                ActivityOptionsCompat.makeSceneTransitionAnimation(
                    this@GlideActivityA,
                    imageView,
                    ViewCompat.getTransitionName(imageView)!!
                )
            startActivity(intent, options.toBundle())
        }
    }

    companion object {
        const val FOX_PIC_URL =
            "https://upload.wikimedia.org/wikipedia/commons/thumb/8/8b/Red_Fox_(Vulpes_vulpes)_(4).jpg/640px-Red_Fox_(Vulpes_vulpes)_(4).jpg"
    }
}