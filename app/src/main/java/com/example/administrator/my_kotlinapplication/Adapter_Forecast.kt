package com.example.administrator.my_kotlinapplication

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import android.widget.TextView

/**
 * Created by Administrator on 2017/12/27/027.
 */
class Adapter_Forecast(var items: List<String>) : RecyclerView.Adapter<Adapter_Forecast.MyHolder>() {
    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: MyHolder?, position: Int) {
        holder?.textView?.text = items[position]
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MyHolder {
        return MyHolder(TextView(parent?.context))
    }

    class MyHolder(var textView: TextView) : RecyclerView.ViewHolder(textView)
}