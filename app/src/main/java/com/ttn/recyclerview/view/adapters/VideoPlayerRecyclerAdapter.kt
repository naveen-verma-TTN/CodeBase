package com.ttn.recyclerview.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.RequestManager
import com.ttn.recyclerview.R
import com.ttn.recyclerview.model.MediaObject

/*
 * Created by Naveen Verma on 29/6/20.
 * Company name: To The New
 * Email ID: naveen.verma@tothenew.com
 */

class VideoPlayerRecyclerAdapter(
    private val requestManager: RequestManager
) : RecyclerView.Adapter<ViewHolder>() {

    private var mediaObjects: ArrayList<MediaObject> = ArrayList()

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        return VideoPlayerViewHolder(
            LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.layout_video_list_item, viewGroup, false)
        )
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        (viewHolder as VideoPlayerViewHolder).onBind(mediaObjects[i], requestManager)
    }

    override fun getItemCount(): Int {
        return mediaObjects.size
    }

    // applying DiffUtils to newly created content
    fun setList(mediaObjects: ArrayList<MediaObject>) {
        val oldVideosList = this.mediaObjects
        val diffResult: DiffUtil.DiffResult = DiffUtil.calculateDiff(
            VideoItemDiffCallback(
                oldVideosList,
                mediaObjects
            )
        )
        this.mediaObjects = mediaObjects
        diffResult.dispatchUpdatesTo(this)
    }

    // DiffUtil --> to increase performance of a RecyclerView
    class VideoItemDiffCallback(
        var oldVideosList: ArrayList<MediaObject>,
        var newVideosList: ArrayList<MediaObject>
    ) : DiffUtil.Callback() {
        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldVideosList[oldItemPosition].media_url == newVideosList[newItemPosition].media_url
        }

        override fun getOldListSize(): Int {
          return oldVideosList.size
        }

        override fun getNewListSize(): Int {
            return newVideosList.size
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
           return oldVideosList[oldItemPosition] == newVideosList[newItemPosition]
        }
    }

    class VideoPlayerViewHolder(var parent: View) :
        ViewHolder(parent) {
        var title: TextView = parent.findViewById(R.id.title)
        var thumbnail: ImageView = parent.findViewById(R.id.thumbnail)
        var volumeControl: ImageView = parent.findViewById(R.id.volume_control)
        var progressBar: ProgressBar = parent.findViewById(R.id.progressBar)
        var requestManager: RequestManager? = null
        fun onBind(mediaObject: MediaObject, requestManager: RequestManager?) {
            this.requestManager = requestManager
            parent.tag = this
            title.text = mediaObject.title
            this.requestManager!!
                .load(mediaObject.thumbnail)
                .into(thumbnail)
        }

    }
}