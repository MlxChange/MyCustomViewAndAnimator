package com.example.mlx.myapplication.view

import android.animation.Animator
import android.animation.Animator.AnimatorListener
import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.os.Handler
import android.os.Message
import android.util.AttributeSet
import android.util.Log


class MySerachView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : Test(context, attrs, defStyleAttr) {

    var mpaint: Paint? = null

    var startAnimator: ValueAnimator? = null
    var endAnimator: ValueAnimator? = null
    var serchAnimator: ValueAnimator? = null

    enum class State {
        None, Starting, Serach, End
    }

    var mCurrentState = State.Starting
    var mCurrentValue = 0f

    var mHandler: Handler? = null

    var mCirclePath: Path? = null
    var mSearchPath: Path? = null

    var mMeasure: PathMeasure? = null

    var mCount = 0

    var mupdateListenr : ValueAnimator.AnimatorUpdateListener?=null
    var mlistener: AnimatorListener? = null



    var isOver = false

    init {
        initpaint()
        initPath()
        inithandler()
        initAnimator()
        mCurrentState = State.Starting
        startAnimator?.start()
    }

    private fun inithandler() {

        mlistener = object : Animator.AnimatorListener {
            override fun onAnimationStart(p0: Animator?) {
            }

            override fun onAnimationEnd(animation: Animator) {
                // getHandle发消息通知动画状态更
                mHandler?.sendEmptyMessage(0)
            }

            override fun onAnimationCancel(animation: Animator) {

            }

            override fun onAnimationRepeat(animation: Animator) {

            }
        }

        mupdateListenr= ValueAnimator.AnimatorUpdateListener { p0 ->
            mCurrentValue= p0?.animatedValue as Float
            //Log.i("mlx",p0?.animatedValue.toString())
            invalidate()
        }

        mHandler = @SuppressLint("HandlerLeak")
        object : Handler() {
            override fun handleMessage(msg: Message?) {
                super.handleMessage(msg)
                //Log.i("mlx","count:+1")
                when (mCurrentState) {
                    State.Starting -> {
                        isOver = false
                        mCurrentState = State.Serach
                        startAnimator?.removeAllListeners()
                        serchAnimator?.start()
                    }
                    State.Serach -> {
                        //Log.i("mlx",isOver.toString())
                        if (!isOver) {
                            serchAnimator?.start()
                            mCount++
                            if (mCount > 3) {
                                isOver = true
                            }
                        } else {
                            mCurrentState = State.End
                            endAnimator?.start()
                        }
                    }
                    State.End -> {
                        mCurrentState = State.None
                    }
                }
            }
        }
    }

    private fun initAnimator() {
        startAnimator = ValueAnimator.ofFloat(0f, 1f).setDuration(2000)
        serchAnimator = ValueAnimator.ofFloat(0f, 1f).setDuration(2000)
        endAnimator = ValueAnimator.ofFloat(1f, 0f).setDuration(2000)



        startAnimator?.addUpdateListener(mupdateListenr)
        serchAnimator?.addUpdateListener(mupdateListenr)
        endAnimator?.addUpdateListener(mupdateListenr)

        startAnimator?.addListener(mlistener)
        endAnimator?.addListener(mlistener)
        serchAnimator?.addListener(mlistener)
    }

    private fun initPath() {
        mCirclePath = Path()
        var oval1 = RectF(-100f, -100f, 100f, 100f)
        mCirclePath?.addArc(oval1, 45f, 359.9f)

        mSearchPath=Path()
        var oval2 = RectF(-50f, -50f, 50f, 50f)
        mSearchPath?.addArc(oval2, 45f, 359.9f)

        var pos = FloatArray(2)
        mMeasure = PathMeasure()
        mMeasure?.setPath(mCirclePath,false)
        mMeasure?.getPosTan(0f, pos, null)
        mSearchPath!!.lineTo(pos[0], pos[1])
        //Log.i("mlx","x:${pos[0]},y:${pos[1]}")

    }

    private fun initpaint() {
        mpaint = Paint()
        mpaint?.strokeWidth = 7f
        mpaint?.color = Color.WHITE
        mpaint?.style = Paint.Style.STROKE
        mpaint?.strokeCap = Paint.Cap.ROUND
        mpaint?.isAntiAlias = true
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawColor(Color.RED)
        drawSearch(canvas)
    }

    private fun drawSearch(canvas: Canvas?) {
        when (mCurrentState) {
            State.None -> {
                canvas?.drawPath(mSearchPath,mpaint)
            }
            State.Starting -> {
                var dst=Path()
                mMeasure?.setPath(mSearchPath,false)
                mMeasure?.getSegment(mMeasure?.length!! *mCurrentValue,mMeasure?.length!!,dst,true)
                canvas?.drawPath(dst,mpaint)
            }
            State.Serach -> {
                mMeasure?.setPath(mCirclePath,false)
                var dst=Path()
                var end=mMeasure!!.length*mCurrentValue
                var start=(end-(0.5-Math.abs(mCurrentValue-0.5))*200f).toFloat()
                Log.i("mlx","start:${start},end:${end},start-end:${start-end}")
                mMeasure?.getSegment(start,end,dst,true)
                canvas!!.drawPath(dst,mpaint)
            }
            State.End -> {
                var dst=Path()
                mMeasure?.setPath(mSearchPath,false)
                mMeasure?.getSegment(mMeasure?.length!!*mCurrentValue,mMeasure?.length!!,dst,true)
                canvas?.drawPath(dst,mpaint)
            }
        }
    }


}