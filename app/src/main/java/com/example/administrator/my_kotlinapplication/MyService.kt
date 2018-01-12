package com.example.administrator.my_kotlinapplication

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log
import kotlin.concurrent.thread


class MyService : Service() {

    private var count: Int = 0
    private val binder = MyBinder()

    inner class MyBinder : Binder() {
        fun getCount(): Int {
            return count
        }
    }
    /*
        必须实现的方法.绑定IBinder对象,应用程序通过该对象与Service通信
     */
    override fun onBind(intent: Intent): IBinder? {
        Log.d("myService", "onBind")
        return binder //返回binder对象
    }

    private var quit: Boolean = false
    /*
    * 在Service第一次被创建后立即回调该方法.
    * */
    override fun onCreate() {
        super.onCreate()
        Log.d("myService", "onCreate")
        thread {
            while (!quit) {
                try {
                    Thread.sleep(1000)
                } catch (e: Exception) {

                }finally {
                    count++
                }
            }
        }
    }

    /*
    * 每次调用StartService(intent)方法是启动该Serviece并回调.
    * */
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d("myService", "onStartCommand")
        return super.onStartCommand(intent, flags, startId)
    }

    /*
    * 在Service被关闭之前回调该方法
    * */
    override fun onDestroy() {
        super.onDestroy()
        Log.d("myService", "onDestroy")
        quit = true
        println("Service is Destroyed")
    }


}
