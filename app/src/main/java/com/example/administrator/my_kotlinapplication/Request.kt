package com.example.administrator.my_kotlinapplication

import android.util.Log
import java.net.URL

/**
 * Created by Administrator on 2017/12/29/029.
 */
class Request(val url: String){
    fun run(): Unit {
        val forecastJsonStr = URL(url).readText()
        Log.d("xxxxxxxx",forecastJsonStr)

    }
}