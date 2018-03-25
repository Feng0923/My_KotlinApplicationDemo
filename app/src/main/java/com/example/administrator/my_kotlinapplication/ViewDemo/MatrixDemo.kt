package com.example.administrator.my_kotlinapplication.ViewDemo

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.KeyEvent
import android.view.MotionEvent
import android.view.View
import com.example.administrator.my_kotlinapplication.R

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

    private lateinit var bitmap: Bitmap
    private var bWidth: Float = 0.0f
    private var bHeight: Float = 0.0f
    init {
        initPaint()
        initBitmap()
        initMatrix()
    }

    @SuppressLint("ResourceAsColor")
    private fun initPaint() {
        mPaint = Paint()
        mPaint.color = R.color.MatrixPaint
        mPaint.isAntiAlias = true
        mPaint.strokeWidth = 50f
        mPaint.strokeCap = Paint.Cap.ROUND
    }

    private lateinit var mMatrix: Matrix
    private lateinit var src: FloatArray
    private lateinit var dst: FloatArray
    fun initMatrix() {
        mMatrix = Matrix()
        src = floatArrayOf(0f,0f,bWidth,0f, bWidth,bHeight,0f,bHeight)
        dst = src.copyOf()
        mMatrix.setPolyToPoly(src,0,dst,0,count)

    }

    private fun initBitmap() {
        bitmap = BitmapFactory.decodeResource(resources,R.drawable.dm)
        bWidth = bitmap.width.toFloat()
        bHeight = bitmap.height.toFloat()
    }
    private lateinit var mPaint: Paint
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.apply {
            translate(100f,100f)
            val invert = Matrix()
            mMatrix.invert(invert)
            drawBitmap(bitmap,invert,null)
            for (i in 0 until count*2 step 2){
                drawPoint(dst[i],dst[i+1],mPaint)
            }
        }
    }

    private val radiu = 100
    private var count = 3
    var currentI: Int = 8
    var currentJ: Int = 8
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when(event?.action){
            MotionEvent.ACTION_DOWN->{
                val x = event?.getX()
                val y = event?.getY()
                for (i in 0 until count*2 step 2){
                    if (Math.abs(x-dst[i])<radiu&&Math.abs(y-dst[i+1])<radiu ){
                        log(i)
                        currentI = i
                        currentJ = i+1
                        break
                    }
                }
            }
            MotionEvent.ACTION_MOVE->{
                val x = event?.getX()
                val y = event?.getY()
                if(currentI in 0 until src.size&&currentJ in 0 until src.size){
                    dst[currentI] = x - 100
                    dst[currentJ] = y - 100
                }
                resetPolyToPoly()
            }
        }
        return true
    }

    private fun resetPolyToPoly() {
//        matrix.reset()
        mMatrix.setPolyToPoly(src, 0, dst, 0, count)
        invalidate()
    }
}