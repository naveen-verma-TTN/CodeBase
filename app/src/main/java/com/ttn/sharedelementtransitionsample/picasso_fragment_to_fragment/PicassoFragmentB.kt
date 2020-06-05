package com.ttn.sharedelementtransitionsample.picasso_fragment_to_fragment

import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.ttn.sharedelementtransitionsample.R

class PicassoFragmentB : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        postponeEnterTransition()
        sharedElementEnterTransition = TransitionInflater.from(context)
            .inflateTransition(android.R.transition.move)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.picasso_fragment_b, container, false)
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        val imageView =
            view.findViewById<View>(R.id.picasso_fragment_b_image) as ImageView
        Picasso.get()
            .load(PicassoFragmentA.GIRAFFE_PIC_URL)
            .fit()
            .noFade()
            .centerCrop()
            .into(imageView, object : Callback {
                override fun onSuccess() {
                    startPostponedEnterTransition()
                }

                override fun onError(e: Exception) {
                    startPostponedEnterTransition()
                }
            })
    }

    companion object {
        fun newInstance(): PicassoFragmentB {
            return PicassoFragmentB()
        }
    }
}