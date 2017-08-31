package com.example.mlx.myapplication.view

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.os.Build
import android.support.annotation.RequiresApi
import android.util.AttributeSet
import android.view.View
import android.view.animation.LinearInterpolator
import java.util.*

/**
 * Created by MLX on 2017/8/21.
 */
class MyWave @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr),View.OnClickListener{
    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun onClick(p0: View?) {
        if(isFirst){
            mValueAnimator?.start()
            isFirst=false
        }else{
            if(!mValueAnimator?.isPaused!!){
                mValueAnimator?.pause()
            }else{
                mValueAnimator?.resume()
            }
        }

    }


    var mScreenWidth=0
    var mScreenHeight=0

    var isFirst=true
    var mCount=0
    var mLength=600

    var mCenterY=0f

    var mWaveHeightTop=0f
    var mPaint:Paint?=null
    var mPath:Path?=null

    var mValueAnimator:ValueAnimator?=null

    var moffset=0f

    init {
        mPaint=Paint()
        mPaint?.color=Color.RED
        mPaint?.style=Paint.Style.FILL_AND_STROKE
        mPaint?.strokeWidth=5f

        mPath=Path()
        mValueAnimator= ValueAnimator.ofFloat(0f, (mLength).toFloat())
        mValueAnimator?.repeatCount=ValueAnimator.INFINITE
        mValueAnimator?.interpolator=LinearInterpolator()
        mValueAnimator?.duration=1000
        mValueAnimator?.repeatMode=ValueAnimator.RESTART
        mValueAnimator?.addUpdateListener{
            moffset= it.animatedValue as Float
            mWaveHeightTop= (Random().nextInt(100)).toFloat()
            invalidate()
        }
        setOnClickListener(this)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        mScreenWidth=w
        mScreenHeight=h
        mCenterY= (mScreenHeight/ 2).toFloat()
        mCount= Math.round( mScreenWidth/mLength +1.5).toInt()

    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        mPath?.reset()
        mPath?.moveTo((-mLength).toFloat()+moffset,mCenterY)
        for (i in 0..mCount){
            mPath?.quadTo((-mLength*3/4).toFloat()+i*mLength+moffset,mCenterY+60, (-mLength/2).toFloat()+i*mLength+moffset,mCenterY)
            mPath?.quadTo((-mLength/4).toFloat()+i*mLength+moffset,mCenterY-60-mWaveHeightTop, (i*mLength).toFloat()+moffset,mCenterY)
        }
        mPath?.lineTo(mScreenWidth.toFloat(), mScreenHeight.toFloat())
        mPath?.lineTo(0f, mScreenHeight.toFloat())
        mPath?.close()
        canvas?.drawPath(mPath,mPaint)
    }

}