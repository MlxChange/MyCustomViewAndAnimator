package com.example.mlx.myapplication.view

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.os.Build
import android.support.annotation.RequiresApi
import android.util.AttributeSet
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator

/**
 * Created by MLX on 2017/8/31.
 */
class PathBezier @JvmOverloads constructor(
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

    var mstartX=100f
    var mStartY=100f

    var mEndX=600f
    var mEndY=600f

    var mControllerX=500f
    var mControllerY=100F

    var mPoint:PointF

    var mPaint: Paint?=null
    var mPath: Path?=null

    var mValueAnimator: ValueAnimator?=null

    init {
        mPoint=PointF()
        mPaint=Paint()
        mPaint?.style=Paint.Style.STROKE
        mPaint?.strokeWidth=5f

        mPath=Path()
        mValueAnimator= ValueAnimator.ofObject(PathBezierEvalator(PointF(mControllerX, mControllerY)),PointF(mstartX,mStartY),PointF(mEndX,mEndY))
        mValueAnimator?.repeatCount=ValueAnimator.INFINITE
        mValueAnimator?.interpolator= AccelerateDecelerateInterpolator()
        mValueAnimator?.duration=1000
        mValueAnimator?.repeatMode=ValueAnimator.RESTART
        mValueAnimator?.addUpdateListener{
            mPoint=it.animatedValue as PointF
            invalidate()
        }
        setOnClickListener(this)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        mPath?.reset()
        mPaint?.color= Color.RED
        canvas?.drawCircle(mstartX,mStartY,20f,mPaint)
        canvas?.drawCircle(mEndX,mEndY,20f,mPaint)
        mPaint?.color=Color.MAGENTA
        mPath?.moveTo(mstartX,mStartY)
        mPath?.quadTo(mControllerX,mControllerY,mEndX,mEndY)
        mPaint?.color=Color.DKGRAY
        canvas?.drawPath(mPath,mPaint)
        canvas?.drawCircle(mPoint.x,mPoint.y,10f,mPaint)
    }

}