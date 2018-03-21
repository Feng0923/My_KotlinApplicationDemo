package com.example.administrator.my_kotlinapplication.ViewDemo

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.View

/**
 * Created by Administrator on 2018/3/21/021.
 */
class MatrixDemo : View {
    constructor(context: Context?,attributeSet: AttributeSet):super(context,attributeSet){}
    constructor(context: Context?) : super(context)

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    private var mWidth = 0f
    private var mHeight = 0f
    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        mWidth = w.toFloat()
        mHeight = h.toFloat()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

    }
}