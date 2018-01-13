package com.example.administrator.my_kotlinapplication

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

/**
 * Created by Administrator on 2017/12/27/027.
 */
class Adapter_Forecast(var context: Context,var items: List<Item>) : RecyclerView.Adapter<Adapter_Forecast.MyHolder>() {
    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: MyHolder?, position: Int) {
        holder?.tv_title?.text = items[position].title
        holder?.tv_content?.text = items[position].content
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MyHolder {
        val v = LayoutInflater.from(context).inflate(R.layout.card, parent, false)
        return MyHolder(v)
    }

    class MyHolder(view: View) : RecyclerView.ViewHolder(view){
        var tv_title: TextView? = null
        var tv_content: TextView? = null
        init {
            tv_title = view.findViewById(R.id.tv_titile) as TextView
            tv_content = view.findViewById(R.id.tv_content) as TextView
        }
    }
}