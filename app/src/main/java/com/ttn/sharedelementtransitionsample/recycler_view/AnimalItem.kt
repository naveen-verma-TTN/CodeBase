package com.ttn.sharedelementtransitionsample.recycler_view

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AnimalItem(var name: String?, var detail: String?, var imageUrl: String?) : Parcelable