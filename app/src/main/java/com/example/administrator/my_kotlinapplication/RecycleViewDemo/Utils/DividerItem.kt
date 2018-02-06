package com.example.administrator.my_kotlinapplication.RecycleViewDemo.Utils

import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import org.jetbrains.anko.forEachChild

/**
 * Created by Administrator on 2018/2/5/005.
 */
class DividerItem(val context: Context,val orientation: Int) : RecyclerView.ItemDecoration() {

     private val mDivider: Drawable
    init {
        val a = context.obtainStyledAttributes(ATTRS)
        mDivider = a.getDrawable(0)
        a.recycle()
    }

    companion object {
        val ATTRS = intArrayOf(android.R.attr.listDivider)
        val HORIZONTAL_LIST: Int = LinearLayoutManager.HORIZONTAL
        val VERTICAL_LIST: Int = LinearLayoutManager.VERTICAL
    }
    /*
    根据传进来的orientation,判断横向竖向
     */
    override fun onDraw(c: Canvas?, parent: RecyclerView?, state: RecyclerView.State?) {
        super.onDraw(c, parent, state)
        when(orientation){
            HORIZONTAL_LIST->drawHorizontal(c,parent)
            VERTICAL_LIST->drawVertical(c,parent)
        }
    }

    /*
    设置item分割线的size
     */
    override fun getItemOffsets(outRect: Rect?, view: View?, parent: RecyclerView?, state: RecyclerView.State?) {
        super.getItemOffsets(outRect, view, parent, state)
        when(orientation){
            VERTICAL_LIST->outRect?.set(0,0,0,mDivider.intrinsicHeight)
            HORIZONTAL_LIST->outRect?.set(0,0,mDivider.intrinsicWidth,0)
        }
    }
    /*
    绘制竖向分割线
     */
    private fun drawVertical(c: Canvas?, parent: RecyclerView?) {
        val left = parent?.paddingLeft?:0
        val right: Int = parent?.width?.minus(parent?.paddingRight) ?:0
       parent?.forEachChild {
           val v = RecyclerView(parent?.context)
           val params = it.layoutParams as RecyclerView.LayoutParams
           val top = it.bottom+params.bottomMargin
           val bottom = top + mDivider.intrinsicHeight
           mDivider.setBounds(left,top,right,bottom)
           mDivider.draw(c)
       }
    }

    private fun drawHorizontal(c: Canvas?, parent: RecyclerView?) {
        val top = parent?.paddingTop?:0
        val bottom: Int = parent?.height?.minus(parent?.paddingBottom) ?:0
        parent?.forEachChild {
            val v = RecyclerView(parent?.context)
            val params = it.layoutParams as RecyclerView.LayoutParams
            val left = it.right+params.rightMargin
            val right = left + mDivider.intrinsicHeight
            mDivider.setBounds(left,top,right,bottom)
            mDivider.draw(c)
        }
    }
}