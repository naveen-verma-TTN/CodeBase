package com.ttn.recyclerview.utils

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration

/*
 * Created by Naveen Verma on 29/6/20.
 * Company name: To The New
 * Email ID: naveen.verma@tothenew.com
 *
 */

class VerticalSpacingItemDecorator(private val verticalSpaceHeight: Int) : ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        outRect.top = verticalSpaceHeight
    }
}