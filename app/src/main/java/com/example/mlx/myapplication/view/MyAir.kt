package com.example.mlx.myapplication.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.example.mlx.myapplication.R

/**
 * Created by MLX on 2017/8/10.
 */
class MyAir @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : Test(context, attrs, defStyleAttr) {

    var mCurrentValue = 0f


    var tan = FloatArray(2)
    var pot = FloatArray(2)
    var bitmap: Bitmap? = null
    internal var matrix: Matrix? = null
    var mpaint = Paint()

    init {
        var options = BitmapFactory.Options()
        options.inSampleSize = 2
        bitmap = BitmapFactory.decodeResource(context.resources, R.mipmap.air, options)
        matrix = Matrix()
        mpaint.style = Paint.Style.STROKE
        mpaint.color = Color.RED
        mpaint.strokeWidth = 7f
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.translate(mwidth,mheight)
        canvas?.scale(1f, -1f)
        canvas?.save()
        var p = Path()
        p.addCircle(0f,0f,300f,Path.Direction.CW)

        mCurrentValue = mCurrentValue.plus(0.005f)
        if (mCurrentValue >= 1) {
            mCurrentValue = 0f
        }

        var meare = PathMeasure(p, false)

        meare.getPosTan(meare.length*mCurrentValue,pot,tan)

        matrix!!.reset()
        var degress = (Math.atan2(tan[1].toDouble(), tan[0].toDouble()) * 180.0 / Math.PI).toFloat()
        matrix?.postRotate(degress, (bitmap!!.width/2).toFloat(), (bitmap!!.height/2).toFloat())
        matrix?.postTranslate(pot[0]-bitmap!!.width/2,pot[1]-bitmap!!.height/2)
        canvas?.drawPath(p, mpaint)
        canvas?.drawBitmap(bitmap, matrix, mpaint)


        invalidate()
    }


}





