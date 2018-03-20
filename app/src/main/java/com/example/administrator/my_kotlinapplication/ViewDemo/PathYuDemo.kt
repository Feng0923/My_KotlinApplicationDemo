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
class PathYuDemo : View {
    constructor(context: Context):super(context)
    constructor(context: Context,attributeSet: AttributeSet):super(context,attributeSet)

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
    }

    private val mPaint = Paint()
    init {
        mPaint.color = Color.BLACK
        mPaint.style = Paint.Style.FILL

    }
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        val path1 = Path()
        val path2 = Path()
        val path3 = Path()
        val path4 = Path()

        path1.addRect(0f,0f,200f,400f,Path.Direction.CW)
        path2.addCircle(200f,200f,200f,Path.Direction.CW)
        path3.addCircle(200f,100f,100f,Path.Direction.CW)
        path4.addCircle(200f,300f,100f,Path.Direction.CW)

        path2.op(path1,Path.Op.INTERSECT)
        path2.op(path4,Path.Op.DIFFERENCE)
        path2.op(path3,Path.Op.UNION)
        canvas?.let {
            it.drawPath(path2,mPaint)
        }
    }
}