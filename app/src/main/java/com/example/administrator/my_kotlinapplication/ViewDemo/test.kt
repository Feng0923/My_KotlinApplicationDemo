package com.example.administrator.my_kotlinapplication.ViewDemo

/**
 * Created by Administrator on 2018/3/7/007.
 */

class test {

    var data: Int = 0
        set(data) {
            field = data
            test()
        }

    fun test() {}
}
