package com.roman.kubik.lastfm.ui.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import android.view.animation.GridLayoutAnimationController
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView


class AnimatedGridRecyclerView : RecyclerView {

    constructor(context: Context) : super(context) {}

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {}

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {}

    override fun setLayoutManager(layout: RecyclerView.LayoutManager?) {
        if (layout is GridLayoutManager) {
            super.setLayoutManager(layout)
        } else {
            throw ClassCastException("You should only use a GridLayoutManager with GridRecyclerView.")
        }
    }

    override fun attachLayoutAnimationParameters(child: View, params: ViewGroup.LayoutParams, index: Int, count: Int) {

        if (adapter != null && layoutManager is GridLayoutManager) {

            var animationParams: GridLayoutAnimationController.AnimationParameters? =
                params.layoutAnimationParameters as? GridLayoutAnimationController.AnimationParameters

            if (animationParams == null) {
                animationParams = GridLayoutAnimationController.AnimationParameters()
                params.layoutAnimationParameters = animationParams
            }

            val columns = (layoutManager as GridLayoutManager).spanCount

            animationParams.count = count
            animationParams.index = index
            animationParams.columnsCount = columns
            animationParams.rowsCount = count / columns

            val invertedIndex = count - 1 - index
            animationParams.column = columns - 1 - invertedIndex % columns
            animationParams.row = animationParams.rowsCount - 1 - invertedIndex / columns

        } else {
            super.attachLayoutAnimationParameters(child, params, index, count)
        }
    }
}