package com.example.administrator.my_kotlinapplication

import org.junit.Test

import org.junit.Assert.*

/**
 * Created by Administrator on 2017/12/28/028.
 */
class MainActivityTest {
    @Test
    fun variableTest() {
        val s = "example"
        val c = s[2] // 这是一个字符'a'
        // 迭代string
        for (c in s) {
            print(c)
        }
    }

}