package com.roman.kubik.lastfm.ui.utils

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView


class GridColumnDecorator(private val spacing: Int) : RecyclerView.ItemDecoration() {


    override fun getItemOffsets(
        outRect: Rect, view: View,
        parent: RecyclerView, state: RecyclerView.State
    ) {

        if (parent.getChildLayoutPosition(view) % 2 == 0) {
            outRect.right = spacing
        } else {
            outRect.left = spacing
        }

        if (parent.getChildLayoutPosition(view) == 0 || parent.getChildLayoutPosition(view) == 1) {
            outRect.top = 0
        } else {
            outRect.top = 2 * spacing
        }
    }
}