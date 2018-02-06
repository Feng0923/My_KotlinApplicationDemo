package com.example.administrator.my_kotlinapplication


import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import org.jetbrains.anko.custom.async
import org.jetbrains.anko.uiThread
import java.net.URL

/**
 * Created by Administrator on 2018/1/9/009.
 */
class PushData : AppWidgetProvider() {
    override fun onReceive(context: Context?, intent: Intent?) {
        super.onReceive(context, intent)
        val remote =RemoteViews(context?.packageName, R.layout.app_layout)
        async {
            val data = URL("https://sslapi.hitokoto.cn/?c=f&encode=text").readText()
            uiThread {  remote.setTextViewText(R.id.show,data) }
        }
    }
    override fun onUpdate(context: Context?, appWidgetManager: AppWidgetManager?, appWidgetIds: IntArray?) {
        val intent = Intent("click")
        val remote =RemoteViews(context?.packageName, R.layout.app_layout)
        val pendingIntent = PendingIntent.getBroadcast(context, R.id.show, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        remote.setOnClickPendingIntent(R.id.show,pendingIntent)

      val componentname = ComponentName(context,PushData::class.java)
        appWidgetManager?.updateAppWidget(componentname,remote)
    }
}