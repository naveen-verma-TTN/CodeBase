package com.ttn.sharedelementtransitionsample.picasso_activity_to_activity

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.ttn.sharedelementtransitionsample.R

class PicassoActivityB : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.picasso_activity_b)
        val imageView =
            findViewById<View>(R.id.picasso_activity_b_image) as ImageView
        supportPostponeEnterTransition()
        Picasso.get()
            .load(PicassoActivityA.TIGER_PIC_URL)
            .fit()
            .noFade()
            .centerCrop()
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