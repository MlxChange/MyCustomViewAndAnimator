package com.example.mlx.myapplication.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent

/**
 * Created by MLX on 2017/8/3.
 */
class Test2 @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : Test(context, attrs, defStyleAttr) {


    var mCollerX=0f
    var mCollerY=0f

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        test5(canvas)
    }

    fun test1(canvas: Canvas?){
        canvas!!.translate(mwidth,mheight)
        var mpaint=Paint()
        mpaint.color=Color.RED
        mpaint.strokeWidth=5f
        mpaint.style=Paint.Style.STROKE
        var p=Path()
        p.lineTo(150f,150f)
        p.lineTo(150f,0f)
        p.close()
        canvas!!.drawPath(p,mpaint)
    }

    fun test2(canvas: Canvas?){
        canvas!!.translate(mwidth,mheight)
        var mpaint=Paint()
        mpaint.color=Color.RED
        mpaint.strokeWidth=5f
        mpaint.style=Paint.Style.STROKE
        var p=Path()
        var rectf=RectF(-200f,-200f,200f,200f)
        p.addRoundRect(rectf,15f,15f,Path.Direction.CW)
        canvas.drawPath(p,mpaint)

    }

    fun test3(canvas: Canvas?){
        canvas!!.translate(mwidth,mheight)
        var mpaint=Paint()
        mpaint.color=Color.RED
        mpaint.strokeWidth=5f
        mpaint.style=Paint.Style.STROKE
        var p =Path()
        var src=Path()
        p.addRect(RectF(-100f,-100f,100f,100f),Path.Direction.CW)
        src.addCircle(0f,0f,100f,Path.Direction.CW)
        p.addPath(src,0f,50f)
        canvas.drawPath(p,mpaint)
    }
    fun test4(canvas: Canvas?){
        canvas!!.translate(mwidth,mheight)
        var mpaint=Paint()
        mpaint.color=Color.RED
        mpaint.strokeWidth=5f
        mpaint.style=Paint.Style.STROKE
        var p=Path()
        var rectf=RectF(-50f,-50f,50f,50f)
        p.addArc(rectf,0f,175f)
        canvas.drawPath(p,mpaint)
    }

    fun test5(canvas: Canvas?){

        var mpaint=Paint()
        mpaint.color=Color.RED
        mpaint.strokeWidth=5f
        mpaint.style=Paint.Style.STROKE
        var p=Path()
        p.moveTo(mwidth/2 - 50,mheight/2)
        p.quadTo(mCollerX,mCollerY,mwidth/2 + 50,mheight/2)
        canvas!!.drawPath(p,mpaint)
    }


    public fun setXY(x:Float,y:Float){
        this.mCollerX=x
        this.mCollerY=y
        invalidate()
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        mCollerX=event!!.x
        mCollerY=event.y
        invalidate()
        return true
    }

}