package com.ttn.recyclerview_item_animation.utilities;

import android.content.Context;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.ttn.recyclerview_item_animation.R;

/**
 * GlideBindingAdapter to set the image
 */
public class GlideBindingAdapter {
    @BindingAdapter("imageUrl")
    public static void setImageResource(ImageView view, int imageUrl) {
        Context context = view.getContext();

        RequestOptions option = new RequestOptions()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background);
        Glide.with(context)
                .setDefaultRequestOptions(option)
                .load(imageUrl)
                .into(view);
    }
}
