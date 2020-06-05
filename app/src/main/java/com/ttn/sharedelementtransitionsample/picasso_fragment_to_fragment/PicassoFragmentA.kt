package com.ttn.sharedelementtransitionsample.picasso_fragment_to_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso
import com.ttn.sharedelementtransitionsample.R

class PicassoFragmentA : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.picasso_fragment_a, container, false)
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        val imageView =
            view.findViewById<View>(R.id.picasso_fragment_a_imageView) as ImageView
        Picasso.get()
            .load(GIRAFFE_PIC_URL)
            .fit()
            .centerCrop()
            .into(imageView)
        val button =
            view.findViewById<View>(R.id.picasso_fragment_a_btn) as Button
        button.setOnClickListener {
            val simpleFragmentB = PicassoFragmentB.newInstance()
            fragmentManager!!.beginTransaction()
                .addSharedElement(imageView, ViewCompat.getTransitionName(imageView)!!)
                .addToBackStack(TAG)
                .replace(R.id.content, simpleFragmentB)
                .commit()
        }
    }

    companion object {
        val TAG = PicassoFragmentA::class.java.simpleName
        @JvmField
        var GIRAFFE_PIC_URL =
            "https://upload.wikimedia.org/wikipedia/commons/thumb/8/8b/Red_Fox_(Vulpes_vulpes)_(4).jpg/640px-Red_Fox_(Vulpes_vulpes)_(4).jpg"

        @JvmStatic
        fun newInstance(): PicassoFragmentA {
            return PicassoFragmentA()
        }
    }
}