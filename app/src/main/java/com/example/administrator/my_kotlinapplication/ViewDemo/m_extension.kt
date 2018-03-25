package com.example.administrator.my_kotlinapplication.ViewDemo

import android.util.Log
import android.view.View

/**
 * Created by Administrator on 2018/3/20/020.
 */
fun View.log(text: Any,search: String = "xxx"){
    Log.d(search,text.toString())
}