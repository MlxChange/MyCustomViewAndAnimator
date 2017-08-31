package com.example.mlx.myapplication

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView

class MainActivity : AppCompatActivity() {


    var imageview: ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    fun wave(v:View){
        startActivity(Intent(this, WaveActivity::class.java))
    }
    fun pathBezier(v:View){
        startActivity(Intent(this, PathBezierActivity::class.java))
    }
    fun heart(v:View){
        startActivity(Intent(this, HeartActivity::class.java))
    }
    fun AirRotate(v:View){
        startActivity(Intent(this, AirRotateActivity::class.java))
    }
    fun SearchView(v:View){
        startActivity(Intent(this, SearchViewActivity::class.java))
    }

}
