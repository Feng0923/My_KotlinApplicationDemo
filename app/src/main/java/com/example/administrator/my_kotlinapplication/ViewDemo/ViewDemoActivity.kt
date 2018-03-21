package com.example.administrator.my_kotlinapplication.ViewDemo

import android.graphics.drawable.Animatable
import android.graphics.drawable.AnimatedVectorDrawable
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.administrator.my_kotlinapplication.R
import kotlinx.android.synthetic.main.activity_view_demo.*

class ViewDemoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_demo)
//        initView()
        initPathView()
    }

    private fun initPathView() {
        val anim = pathDemo_image.drawable as AnimatedVectorDrawable
        pathDemo_image.setOnClickListener { anim.start() }
//        anim.start()
    }

    private fun initView() {
        val arrayList = ArrayList<PieData>()
        for(i in 1..6){
            arrayList.add(PieData("$i",i.toFloat()))
        }
//        pieView.mData = arrayList
    }
}
