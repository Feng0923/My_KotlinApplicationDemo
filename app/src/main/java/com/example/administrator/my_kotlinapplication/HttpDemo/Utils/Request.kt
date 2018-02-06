package com.example.administrator.my_kotlinapplication.HttpDemo.Utils

import java.net.URL

/**
 * Created by Administrator on 2018/2/6/006.
 */
interface Request {
    fun openUrl(url: URL,requestMethod: String = "get")
}