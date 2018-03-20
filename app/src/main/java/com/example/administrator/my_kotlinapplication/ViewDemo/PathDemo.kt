package com.example.administrator.my_kotlinapplication.ViewDemo

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View

/**
 * Created by Administrator on 2018/3/7/007.
 */
class PathDemo : View{
    constructor(context: Context):super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {}

    var mWidth = 0f
    var mHeight = 0f
    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        mWidth = w.toFloat()
        mHeight = h.toFloat()
    }
    val mPaint = Paint()
    init {
        mPaint.color = Color.BLACK
        mPaint.style = Paint.Style.STROKE
        mPaint.strokeWidth = 5f
    }
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        val path = Path()
        path.lineTo(200f,500f)
        path.lineTo(400f,250f)
        canvas?.let {
            it.drawPath(path,mPaint)
        }
    }
}