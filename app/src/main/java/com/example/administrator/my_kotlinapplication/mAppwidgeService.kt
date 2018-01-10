package com.example.administrator.my_kotlinapplication

import android.app.Service
import android.appwidget.AppWidgetManager
import android.content.ComponentName
import android.content.Intent
import android.os.IBinder
import android.provider.SyncStateContract.Helpers.update
import android.util.Log
import android.widget.RemoteViews
import org.jetbrains.anko.async
import org.jetbrains.anko.uiThread
import java.net.URL
import java.security.Provider
import java.util.*
import kotlin.math.log

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

    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val mTimer = Timer()
        Log.d("service11","service")
        update()
        mTimer.schedule(object : TimerTask() {
            override fun run() {
                Log.d("service11","servicewww")
                update()
            }
        }, 0, 1000*60)
        return super.onStartCommand(intent, flags, startId)
    }

    private fun update() {
        val remote = RemoteViews(packageName,R.layout.m_app_widget)
        async {
            val data = URL("https://sslapi.hitokoto.cn/?c=f&encode=text").readText()

            uiThread {
                remote.setTextViewText(R.id.appwidget_text, data)
                Log.d("asdfs", data)
                val con = ComponentName(applicationContext, mAppWidget::class.java)
                val appWidgetManager = AppWidgetManager.getInstance(applicationContext)
                appWidgetManager.updateAppWidget(con, remote)
            }
        }
    }


}