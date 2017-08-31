package com.example.mlx.myapplication.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet

/**
 * Created by MLX on 2017/8/9.
 */
class Heart @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : Test(context, attrs, defStyleAttr) {

    val C = 0.551915024494f

    var mCirleRaius = 200f
    var mDiffreents = mCirleRaius * C

    var pointf = PointF(0f, 0f)

    var mdata = arrayOf<Float>(8f)
    var mctrol = arrayOf<Float>(16f)

    var mDuration = 100f
    var mCount = 10f
    var mCureent = 0

    var mpaint = Paint()


    init {
        mdata[0] = 0f
        mdata[1] = mCirleRaius

        mdata[2] = mCirleRaius
        mdata[3] = 0f

        mdata[4] = 0f
        mdata[5] = -mCirleRaius

        mdata[6] = -mCirleRaius
        mdata[7] = 0f

        mctrol[0] = mdata[0]?.plus(mDiffreents)
        mctrol[1] = mdata[1]

        mctrol[2] = mdata[2]
        mctrol[3] = mdata[3]?.plus(mDiffreents)

        mctrol[4] = mdata[2]
        mctrol[5] = mdata[3]?.minus(mDiffreents)

        mctrol[6] = mdata[4]?.plus(mDiffreents)
        mctrol[7] = mdata[5]

        mctrol[8] = mdata[4]?.minus(mDiffreents)
        mctrol[9] = mdata[5]

        mctrol[10] = mdata[6]
        mctrol[11] = mdata[7]?.minus(mDiffreents)

        mctrol[12] = mdata[6]
        mctrol[13] = mdata[7]?.plus(mDiffreents)

        mctrol[14] = mdata[0]?.minus(mDiffreents)
        mctrol[15] = mdata[1]


    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        drawCoordinateSystem(canvas)
        drawPoint(canvas)
        drawCircle(canvas)

    }

    private fun drawCircle(canvas: Canvas?) {
        canvas!!.save()
        canvas.translate(mwidth, mheight)
        canvas.scale(1f, -1f)
        var p = Path()
        mpaint.color = Color.RED
        mpaint.style = Paint.Style.FILL
        p.moveTo(mdata.get(0)!!, mdata.get(1)!!)
        p.cubicTo(mctrol.get(0)!!, mctrol.get(1)!!, mctrol.get(2)!!, mctrol.get(3)!!, mdata[2]!!, mdata[3]!!)
        p.cubicTo(mctrol.get(4)!!, mctrol.get(5)!!, mctrol.get(6)!!, mctrol.get(7)!!, mdata[4]!!, mdata[5]!!)
        p.cubicTo(mctrol.get(8)!!, mctrol.get(9)!!, mctrol.get(10)!!, mctrol.get(11)!!, mdata[6]!!, mdata[7]!!)
        p.cubicTo(mctrol.get(12)!!, mctrol.get(13)!!, mctrol.get(14)!!, mctrol.get(15)!!, mdata[0]!!, mdata[1]!!)
        canvas.drawPath(p, mpaint)
//        if(mCureent<=mDuration){
//            mdata[1] = mdata[1]?.minus(140/mCount)
//            mctrol[4]=mctrol[4]?.minus(30/mCount)
//            mctrol[7]=mctrol[7]?.plus(110/mCount)
//            mctrol[9]=mctrol[9]?.plus(110/mCount)
//            mctrol[10]=mctrol[10]?.plus(30/mCount)
//            Log.i("mlx","${mctrol[8]}")
//            mCureent=mCureent+mCount.toInt()
//            postInvalidateDelayed(100)
//        }
        if (mCureent <= mDuration) {
            if (mCureent < 20) {
                mdata[0] = mdata[0]?.plus(100)
            }
        }

    }


    private fun drawPoint(canvas: Canvas?) {
        canvas!!.save()
        canvas.translate(mwidth, mheight)
        canvas.scale(1f, -1f)
        var systempaint = Paint()
        systempaint.style = Paint.Style.FILL
        systempaint.color = Color.BLUE
        systempaint.strokeWidth = 7f
        for (i in 0..7) {
            if (i % 2 == 0) {
                canvas.drawPoint(mdata.get(i)!!, mdata.get(i + 1)!!, systempaint)
            }
        }
        systempaint.color = Color.GREEN
        for (i in 0..15) {
            if (i % 2 == 0) {
                canvas.drawPoint(mctrol.get(i)!!, mctrol.get(i + 1)!!, systempaint)
            }
        }
        canvas.restore()
    }

    private fun drawCoordinateSystem(canvas: Canvas?) {
        canvas!!.save()
        canvas.translate(mwidth, mheight)
        canvas.scale(1f, -1f)
        var systempaint = Paint()
        systempaint.style = Paint.Style.FILL
        systempaint.color = Color.RED
        systempaint.strokeWidth = 5f
        canvas.drawLine(-2000f, 0f, 2000f, 0f, systempaint)
        canvas.drawLine(0f, -2000f, 0f, 2000f, systempaint)
        canvas.restore()
    }


}


