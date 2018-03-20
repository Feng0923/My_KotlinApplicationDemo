package com.example.administrator.my_kotlinapplication.ViewDemo

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View

/**
 * Created by Administrator on 2018/3/7/007.
 */

class PieView : View {

    private val mColors = intArrayOf(0xFFCCFF00.toInt(), 0xFF6495ED.toInt(), 0xFFE32636.toInt(), 0xFF800000.toInt(), 0xFF808000.toInt(), 0xFFFF8C69.toInt(), 0xFF808080.toInt(), 0xFFE6B800.toInt(), 0xFF7CFC00.toInt())
     var mStartAngle = 0f
    set(value) {field = value;invalidate()}
    var mData: ArrayList<PieData>? = null
    set(value) {field = value;initData();invalidate()}

    private fun initData() {
        mData?.let {
            var sumValue = 0f
            var i = 0
            for (item in it){
                sumValue += item.value
                val index = i++ % mColors.size
                item.color = mColors[index]
            }
            var sumAngle = 0f
            for (item in it){
                val percentage = item.value/sumValue
                val  angle = percentage*360
                item.percentage = percentage
                item.angle = angle
                sumAngle += angle
            }
        }
    }

    private var mWidth = 0f
    private var mHeigth = 0f
    private val paint = Paint()

    init {
        paint.style = Paint.Style.FILL
        paint.isAntiAlias = true
    }
    constructor(context: Context) : super(context) {}
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {}

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        mWidth = w.toFloat()
        mHeigth = h.toFloat()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        mData?.let {
            var currentStartAngle = mStartAngle
            canvas?.translate(mWidth/2,mHeigth/2) //坐标系的移动,位移是基于当前位置移动，
            val radius = Math.min(mHeigth,mWidth)/2*0.8.toFloat()
            val rect = RectF(-radius,-radius,radius,radius)
            for(item in it){
                paint.color = item.color
                canvas?.drawArc(rect, currentStartAngle,item.angle,true,paint)
                currentStartAngle+=item.angle
            }
        }



    }

}
