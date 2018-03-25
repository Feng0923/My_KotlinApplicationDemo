package com.example.administrator.my_kotlinapplication.ViewDemo

import android.content.Context
import android.graphics.*
import android.os.Handler
import android.os.Message
import android.util.AttributeSet
import android.view.View
import com.example.administrator.my_kotlinapplication.R
import kotlinx.android.synthetic.main.recycleritem.view.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import kotlin.concurrent.thread

/**
 * Created by Administrator on 2018/3/7/007.
 */
class PictureTextDemo : View{
    constructor(context: Context):super(context)
    constructor(context: Context,attributeSet: AttributeSet):super(context,attributeSet){ recording2()}

    var mWidth = 0f
    var mHeight = 0f
    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        mWidth  = w.toFloat()
        mHeight = h.toFloat()
    }
    val mPaint = Paint()
    init {
        mPaint.color = Color.BLACK
        mPaint.strokeWidth = 5f
    }

    lateinit var bitmap: Bitmap
    var bWidtht = 0
    var bHeight = 0
    val pages = 13
    var currentPage = 0
    val duration = 500

    val hander = object : Handler() {
        override fun handleMessage(msg: Message?) {
            super.handleMessage(msg)
            if(currentPage<pages-1){
                invalidate()
                currentPage++
                this.sendEmptyMessageDelayed(0,duration/pages.toLong())
            }
        }
    }
    private fun check() {
        bitmap = BitmapFactory.decodeResource(resources,R.drawable.checkmark)
        bHeight = bitmap.height
        bWidtht = bitmap.width/pages
        hander.sendEmptyMessageDelayed(0,duration/pages.toLong())
//        doAsync {
//            thread {
//                while (currentPage<pages){
//                    currentPage++
//                    Thread.sleep(duration/pages.toLong())
//                }
//            }
//            uiThread { invalidate() }
//        }
    }

    val mPicture = Picture()
    private fun recording() {
        val pWidth = 1000
        val pHeight = 500
        val canvas = mPicture.beginRecording(pWidth,pHeight)
        mPaint.style = Paint.Style.FILL
        canvas.translate(pWidth.toFloat()/2,pHeight.toFloat()/2)
        canvas.drawCircle(0f,0f,200f,mPaint)
        mPicture.endRecording()
    }

    private fun recording2(){
        val pWidth = 1000
        val pHeight = 1000
        val canvas = mPicture.beginRecording(pWidth,pHeight)
        val bitmap = BitmapFactory.decodeResource(resources, R.drawable.dm)
        val rect = Rect(1800,0,2500,1000)
        val rectf = Rect(0,0,400,400)
//        canvas.drawBitmap(bitmap,0f,0f,mPaint)
        canvas.drawBitmap(bitmap,rect,rectf,mPaint)
        mPicture.endRecording()

    }
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
//        canvas?.drawPicture(mPicture)
//        val rect = Rect(currentPage*bWidtht,0,(currentPage+1)*bWidtht,bHeight)
//        val rectf = Rect(0,0,bWidtht+10,bHeight+10)
//        canvas?.drawBitmap(bitmap,rect,rectf,null)

        val textPaint = Paint()
        textPaint.color = Color.BLACK
        textPaint.style = Paint.Style.FILL
        textPaint.textSize = 50f
        val text = "Veng"
        canvas?.let {
            it.drawText(text,200f,500f,textPaint)
            it.drawText(text,0,1,200f,600f,textPaint)//截取文字

            it.drawPosText(text, floatArrayOf(100f,100f,200f,200f,300f,300f,400f,400f),textPaint)
        }

    }
}