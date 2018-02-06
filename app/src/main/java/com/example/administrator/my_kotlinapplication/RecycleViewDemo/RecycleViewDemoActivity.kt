package com.example.administrator.my_kotlinapplication.RecycleViewDemo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import com.example.administrator.my_kotlinapplication.R
import com.example.administrator.my_kotlinapplication.RecycleViewDemo.Utils.DividerItem
import com.example.administrator.my_kotlinapplication.RecycleViewDemo.Utils.RecycleAdapter
import kotlinx.android.synthetic.main.activity_recycle_view_demo.*


class RecycleViewDemoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycle_view_demo)
        initView()
    }

    private fun initView() {
        forecast_list.layoutManager =  LinearLayoutManager(this)//设置布局管理器
        forecast_list.itemAnimator = DefaultItemAnimator()//设置item增加和删除时的动画
        val data: Array<String> = resources.getStringArray(R.array.recycleList)
        val adapter = RecycleAdapter(this,data.plus("梁胜峰"))
        forecast_list.adapter = adapter
        forecast_list.addItemDecoration(DividerItem(this,DividerItem.VERTICAL_LIST))
    }
}
