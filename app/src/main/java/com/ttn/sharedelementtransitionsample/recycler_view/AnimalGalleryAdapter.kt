package com.ttn.sharedelementtransitionsample.recycler_view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.ttn.sharedelementtransitionsample.R
import com.ttn.sharedelementtransitionsample.recycler_view.AnimalGalleryAdapter.ImageViewHolder
import java.util.*

class AnimalGalleryAdapter(
    private val animalItems: ArrayList<AnimalItem>,
    private val animalItemClickListener: AnimalItemClickListener
) : RecyclerView.Adapter<ImageViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        return ImageViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_animal_square, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return animalItems.size
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val animalItem = animalItems[position]
        Picasso.get()
            .load(animalItem.imageUrl)
            .into(holder.animalImageView)
        ViewCompat.setTransitionName(holder.animalImageView, animalItem.name)
        holder.itemView.setOnClickListener {
            animalItemClickListener.onAnimalItemClick(
                holder.adapterPosition,
                animalItem,
                holder.animalImageView
            )
        }
    }

    class ImageViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
         val animalImageView: ImageView = itemView.findViewById<View>(R.id.item_animal_square_image) as ImageView
    }

}