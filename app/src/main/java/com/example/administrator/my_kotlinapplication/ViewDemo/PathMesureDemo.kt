package com.example.administrator.my_kotlinapplication.ViewDemo

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.example.administrator.my_kotlinapplication.R
import kotlin.math.log

/**
 * Created by Administrator on 2018/3/20/020.
 */
class PathMesureDemo : View {
    constructor(context: Context):super(context)
    constructor(context: Context,attributeSet: AttributeSet):super(context,attributeSet)

    init {
        initPaint()
    }
    private lateinit var mPaint: Paint
    private var lineWidth = 5f
    @SuppressLint("ResourceAsColor")
    private fun initPaint() {
        mPaint = Paint()
        mPaint.color = R.color.lineColorDefault
        mPaint.style = Paint.Style.STROKE
        mPaint.strokeWidth = lineWidth
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    private var mWight = 0f
    private var mHeight = 0f
    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        mWight = w.toFloat()
        mHeight = h.toFloat()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.apply {
            translate(mWight / 2,mHeight / 2)
            val path = Path()
            val point = PointF(0f,200f)
            path.lineTo(point.x,point.y)
            val point2 = PointF(200f,200f)
            path.lineTo(point2.x,point2.y)
            val point3 = PointF(200f,0f)
            path.lineTo(point3.x,point3.y)
            val measure1 = PathMeasure(path,false)
            val measure2 = PathMeasure(path,true)
            drawPath(path,mPaint)
            log("${measure1.length}---${measure2.length}","PathView")
        }
    }
}