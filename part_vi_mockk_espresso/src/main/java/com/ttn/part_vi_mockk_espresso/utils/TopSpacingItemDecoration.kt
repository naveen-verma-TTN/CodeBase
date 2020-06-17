package com.ttn.part_vi_mockk_espresso.utils

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

/*
 * Created by Naveen Verma on 17/6/20.
 * Company name: To The New
 * Email ID: naveen.verma@tothenew.com
 */

class TopSpacingItemDecoration(private val padding: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.top = padding
    }
}