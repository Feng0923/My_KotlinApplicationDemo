package com.example.administrator.my_kotlinapplication.ViewDemo

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import java.util.jar.Attributes

/**
 * Created by Administrator on 2018/3/7/007.
 */
class CanvasDemo: View {
    val mPaint = Paint()
    init {
        mPaint.color = Color.BLACK
        mPaint.style = Paint.Style.STROKE
        mPaint.strokeWidth = 20f
    }
    constructor(context: Context):super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {}
    var mWidth = 0f
    var mHeight = 0f
    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        mWidth = w.toFloat()
        mHeight = h.toFloat()
    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)


        canvas?.translate(mWidth/2,mHeight/2)
        val rectf = RectF(-400f,-400f,400f,400f)
        for (i in 0..20){
            canvas?.scale(0.9f,0.9f)
            canvas?.drawRect(rectf,mPaint)
        }
        canvas?.save()

        canvas?.let {
            mPaint.strokeWidth = 5f
            it.translate(mWidth/2,mHeight/2)
            canvas.drawCircle(0f,0f,400f,mPaint)
            canvas.drawCircle(0f,0f,380f,mPaint)
            for (i in 0..360 step 10){
                canvas.drawLine(0f,380f,0f,400f,mPaint)
                canvas.rotate(10f)
            }
            canvas.restore()
        }
    }
}