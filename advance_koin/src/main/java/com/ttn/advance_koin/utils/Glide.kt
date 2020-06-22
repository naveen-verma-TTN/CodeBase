package com.ttn.advance_koin.utils

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.bumptech.glide.RequestManager
import org.koin.core.KoinComponent
import org.koin.core.inject


/*
 * Created by Naveen Verma on 22/6/20.
 * Company name: To The New
 * Email ID: naveen.verma@tothenew.com
 */

object GlideInstance : KoinComponent {
    val glide: RequestManager by inject()
}

@BindingAdapter("imageUrl")
fun ImageView.bindImage(imageUrl: String?) {
    imageUrl?.let { url ->
        val parsedImageUrl = url.toUri().buildUpon().scheme("https").build()
        GlideInstance.glide
            .load(parsedImageUrl)
            .into(this)
    }
}