package com.example.administrator.my_kotlinapplication.RecycleViewDemo.Utils

import android.content.Context
import android.support.design.widget.Snackbar
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.example.administrator.my_kotlinapplication.R

/**
 * Created by Administrator on 2018/2/5/005.
 */
class RecycleAdapter(val mContext: Context, var mList: Array<String>) : RecyclerView.Adapter<RecycleAdapter.mHolder>() {

    override fun onBindViewHolder(holder: mHolder?, position: Int) {
        holder?.text?.apply {
            text = mList.get(position)

//            setOnClickListener {
//                Snackbar.make(it,"标题",Snackbar.LENGTH_SHORT).setAction("event", View.OnClickListener {
//                    Toast.makeText(mContext,"toast",Toast.LENGTH_LONG).show()
//                }).setDuration(Snackbar.LENGTH_SHORT).show()
//            }
        }

    }

    override fun getItemCount(): Int {
        return mList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): mHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.recycleritem,null,false)
        val holder = mHolder(view)
        return holder
    }

    class mHolder(val view: View) : RecyclerView.ViewHolder(view){
        var text: TextView
        init {
            text = view.findViewById(R.id.text) as TextView
        }
    }
}