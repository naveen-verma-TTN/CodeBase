package com.ttn.recyclerview_item_animation

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.ttn.recyclerview_item_animation.databinding.ItemNewsBinding


/**
 * Recycler Adapter Class
 */
class NewsAdapter : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {
    private var mData = ArrayList<News>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ItemNewsBinding = ItemNewsBinding.inflate(
            layoutInflater, parent, false
        )
        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(mData[position])
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    fun setList(list: ArrayList<News>) {
        mData = list
    }

    /**
     * ViewHolder Class
     */
    class NewsViewHolder(private val binding: ItemNewsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        // fun to bind the data
        fun bind(news: News) {
            setAnimation()

            binding.news = news

        }

        // fun to setAnimation on card
        private fun setAnimation() {
            binding.rvItemImageUser.animation =
                AnimationUtils.loadAnimation(itemView.context, R.anim.fade_transition_animation)
            binding.container.animation = AnimationUtils.loadAnimation(
                itemView.context,
                R.anim.fade_transition_animation_card
            )
        }
    }
}