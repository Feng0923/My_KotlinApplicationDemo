package com.example.administrator.my_kotlinapplication.ViewDemo

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

/**
 * Created by Administrator on 2018/3/7/007.
 */
class BezierDemo : View{
    constructor(context: Context):super(context)
    constructor(context: Context,attributeSet: AttributeSet):super(context,attributeSet)

    private var mWidth = 0f
    private var mHeight =0f


    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        mWidth = w.toFloat()
        mHeight = h.toFloat()
        val centerX = mWidth / 2
        val centerY = mHeight / 2
        startPoint.x = centerX -200
        startPoint.y = centerY
        endPoint.x = centerX+200
        endPoint.y = centerY
        controlPoint.x = centerX
        controlPoint.y = centerY-100

    }
    private lateinit var startPoint: PointF
    private lateinit var endPoint: PointF
    private lateinit var controlPoint: PointF
    private val mPaint = Paint()
    init {
        mPaint.color = Color.BLACK
        mPaint.strokeWidth = 8f
        mPaint.style = Paint.Style.STROKE
        startPoint = PointF(0f,0f)
        endPoint = PointF(0f,0f)
        controlPoint = PointF(0f,0f)
    }
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.let {
            val path = Path()
            path.moveTo(startPoint.x,startPoint.y)
            path.quadTo(controlPoint.x,controlPoint.y,endPoint.x,endPoint.y)
            canvas.drawPath(path,mPaint)
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        event?.let {
            controlPoint.x = it.getX()
            controlPoint.y = it.getY()
            invalidate()
        }
        return true
    }
}