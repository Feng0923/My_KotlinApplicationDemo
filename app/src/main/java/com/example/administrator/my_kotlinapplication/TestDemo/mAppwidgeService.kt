package com.example.administrator.my_kotlinapplication.TestDemo

import android.app.AlarmManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder
import android.os.SystemClock


/**
 * Created by Administrator on 2018/1/10/010.
 */
class mAppwidgeService : Service() {
//    var thread: Thread? = null
    override fun onBind(intent: Intent?): IBinder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreate() {
        super.onCreate()
        alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
    }
     lateinit var alarmManager: AlarmManager

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
//        val mTimer = Timer()
//        Log.d("service11","service")
//        update()
//        mTimer.schedule(object : TimerTask() {
//            override fun run() {
//                Log.d("service11","servicewww")
//                update()
//            }
//        }, 0, 1000*60)
        val millis: Long = 1000*3
        val mIntent = Intent("android.appwidget.action.APPWIDGET_UPDATE")
        mIntent.setClass(applicationContext, mAppWidget::class.java)
        val pendingIntent = PendingIntent.getBroadcast(applicationContext,0,mIntent,0)
        alarmManager.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime(), millis,pendingIntent)
        return super.onStartCommand(intent, flags, startId)
    }

    private fun update() {
        val intent = Intent("android.appwidget.action.APPWIDGET_UPDATE")
        sendBroadcast(intent)
    }


}