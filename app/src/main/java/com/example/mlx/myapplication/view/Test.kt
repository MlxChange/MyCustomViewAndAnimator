package com.example.mlx.myapplication.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View


open class Test @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    var mwidth=0f
    var mheight=0f

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.translate(mwidth,mheight)

    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        mwidth=w.toFloat()/2
        mheight=h.toFloat()/2

    }

}