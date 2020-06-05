package com.ttn.sharedelementtransitionsample.recycler_view

import android.view.View
import android.widget.ImageView

interface AnimalItemClickListener {
    fun onAnimalItemClick(
        pos: Int,
        animalItem: AnimalItem,
        shareImageView: View
    )
}