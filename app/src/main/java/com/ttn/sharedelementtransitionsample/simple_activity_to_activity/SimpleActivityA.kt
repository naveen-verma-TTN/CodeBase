package com.ttn.sharedelementtransitionsample.simple_activity_to_activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.core.view.ViewCompat
import com.ttn.sharedelementtransitionsample.R

class SimpleActivityA : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_a)
        val imageView =
            findViewById<View>(R.id.simple_activity_a_imageView) as ImageView
        val button =
            findViewById<View>(R.id.simple_activity_a_btn) as Button
        button.setOnClickListener {
            val intent = Intent(this@SimpleActivityA, SimpleActivityB::class.java)
            val options =
                ActivityOptionsCompat.makeSceneTransitionAnimation(
                    this@SimpleActivityA,
                    imageView,
                    ViewCompat.getTransitionName(imageView)!!
                )
            startActivity(intent, options.toBundle())
        }
    }
}