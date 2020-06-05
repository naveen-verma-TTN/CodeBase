package com.ttn.sharedelementtransitionsample.recycler_view.recycler_view_to_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ttn.sharedelementtransitionsample.R
import com.ttn.sharedelementtransitionsample.recycler_view.AnimalGalleryAdapter
import com.ttn.sharedelementtransitionsample.recycler_view.AnimalItem
import com.ttn.sharedelementtransitionsample.recycler_view.AnimalItemClickListener
import com.ttn.sharedelementtransitionsample.recycler_view.Utils
import com.ttn.sharedelementtransitionsample.recycler_view.recycler_view_to_fragment.AnimalDetailFragment.Companion.newInstance

class RecyclerViewFragment : Fragment(),
    AnimalItemClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.activity_recycler_view, container, false)
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        val animalGalleryAdapter = AnimalGalleryAdapter(
            Utils.generateAnimalItems(context!!),
            this
        )
        val recyclerView =
            view.findViewById<View>(R.id.recycler_view) as RecyclerView
        val gridLayoutManager = GridLayoutManager(context, 2)
        recyclerView.layoutManager = gridLayoutManager
        recyclerView.adapter = animalGalleryAdapter
    }

    override fun onAnimalItemClick(
        pos: Int,
        animalItem: AnimalItem,
        shareImageView: View
    ) {
        val animalDetailFragment: Fragment = newInstance(
            animalItem,
            ViewCompat.getTransitionName(shareImageView)
        )
        fragmentManager!!.beginTransaction()
            .addSharedElement(shareImageView, ViewCompat.getTransitionName(shareImageView)!!)
            .addToBackStack(TAG)
            .replace(R.id.content, animalDetailFragment)
            .commit()
    }

    companion object {
        val TAG =
            RecyclerViewFragment::class.java.simpleName

        @JvmStatic
        fun newInstance(): RecyclerViewFragment {
            return RecyclerViewFragment()
        }
    }
}