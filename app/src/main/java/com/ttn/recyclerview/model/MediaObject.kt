package com.ttn.recyclerview.model

/*
 * Created by Naveen Verma on 29/6/20.
 * Company name: To The New
 * Email ID: naveen.verma@tothenew.com
 */
data class MediaObject(
    val title: String,
    val media_url: String,
    val thumbnail: String,
    val description: String
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as MediaObject

        if (title != other.title) return false
        if (media_url != other.media_url) return false
        if (thumbnail != other.thumbnail) return false
        if (description != other.description) return false

        return true
    }

    override fun hashCode(): Int {
        var result = title.hashCode()
        result = 31 * result + media_url.hashCode()
        result = 31 * result + thumbnail.hashCode()
        result = 31 * result + description.hashCode()
        return result
    }
}
