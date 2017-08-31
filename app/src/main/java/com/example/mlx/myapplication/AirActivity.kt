package com.example.mlx.myapplication

import android.graphics.drawable.Animatable
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView

class AirActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_air)
    }

    fun airAnim(v: View){
        var imgView=v as ImageView
        val drawable = imgView.drawable
        if(drawable is Animatable){
            val animatable = drawable as Animatable
            animatable.start()
        }
    }
}
