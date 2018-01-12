package com.example.administrator.my_kotlinapplication

import org.jsoup.Jsoup
import org.junit.Test

import org.junit.Assert.*
import java.net.URL

/**
 * Created by Administrator on 2017/12/28/028.
 */
class MainActivityTest {
    @Test
    fun variableTest() {
        val document = Jsoup.connect("http://musicmini.baidu.com/app/search/searchList.php?qword={海阔天空}&ie=utf-8&page={1}").get()
        val element = document.select("div.box").first()
        val select = element.select("a")
        val select1 = select.select("[onClick]")[0]
        println(select1)
    }

}