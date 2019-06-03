package com.geekorum.includedatabindingbug

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.FrameLayout

/**
 * CustomViewGroup with an inner hidden contentView.
 * All child of this ViewGroup are added to contentView
 */
class CustomViewGroup
    @JvmOverloads constructor(context: Context, attrs: AttributeSet?=null, defStyleAttr: Int = 0) :
    FrameLayout(context, attrs, defStyleAttr) {

    private val contentView: FrameLayout = FrameLayout(context)

    init {
        super.addView(contentView, -1, LayoutParams(MATCH_PARENT, MATCH_PARENT))
    }

    override fun addView(child: View?, index: Int, params: ViewGroup.LayoutParams?) {
        contentView.addView(child, index, params)
    }

}
