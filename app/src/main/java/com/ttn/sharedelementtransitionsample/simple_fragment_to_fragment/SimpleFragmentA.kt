package com.ttn.sharedelementtransitionsample.simple_fragment_to_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import com.ttn.sharedelementtransitionsample.R

class SimpleFragmentA : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_a, container, false)
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        val imageView =
            view.findViewById<View>(R.id.fragment_a_imageView) as ImageView
        val button =
            view.findViewById<View>(R.id.fragment_a_btn) as Button
        button.setOnClickListener {
            val simpleFragmentB = SimpleFragmentB.newInstance()
            fragmentManager!!.beginTransaction()
                .addSharedElement(imageView, ViewCompat.getTransitionName(imageView)!!)
                .addToBackStack(TAG)
                .replace(R.id.content, simpleFragmentB)
                .commit()
        }
    }

    companion object {
        val TAG = SimpleFragmentA::class.java.simpleName
        fun newInstance(): SimpleFragmentA {
            return SimpleFragmentA()
        }
    }
}