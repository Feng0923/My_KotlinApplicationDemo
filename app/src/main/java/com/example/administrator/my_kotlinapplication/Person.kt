package com.example.administrator.my_kotlinapplication

/**
 * Created by Administrator on 2017/12/27/027.
 */
open class Person(name: String) {
    init {
        println(name)
    }
}

class Strudent(name: String,id: Int) : Person(name)