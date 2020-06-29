package com.ttn.recyclerview.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.RequestManager
import com.ttn.recyclerview.R
import com.ttn.recyclerview.model.MediaObject
import java.util.*

/*
 * Created by Naveen Verma on 29/6/20.
 * Company name: To The New
 * Email ID: naveen.verma@tothenew.com
 */

class VideoPlayerRecyclerAdapter(
    private val mediaObjects: ArrayList<MediaObject>,
    private val requestManager: RequestManager
) : RecyclerView.Adapter<ViewHolder>() {
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

    class VideoPlayerViewHolder(var parent: View) :
        ViewHolder(parent) {
        var media_container: FrameLayout = parent.findViewById(R.id.media_container)
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