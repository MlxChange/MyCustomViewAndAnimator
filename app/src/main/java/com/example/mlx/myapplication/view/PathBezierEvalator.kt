package com.example.mlx.myapplication.view

import android.animation.TypeEvaluator
import android.graphics.PointF

/**
 * Created by MLX on 2017/8/31.
 */
class PathBezierEvalator(pointf:PointF):TypeEvaluator<PointF> {

    var mpointf:PointF

    init {
        this.mpointf=pointf
    }


    override fun evaluate(p0: Float, p1: PointF?, p2: PointF?): PointF {
        return PointF().CalculatiooBezier(p0,p1,mpointf,p2)
    }
}