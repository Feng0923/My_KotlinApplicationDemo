package com.example.administrator.my_kotlinapplication.TestDemo

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.RemoteViews
import com.example.administrator.my_kotlinapplication.R
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.net.URL

/**
 * Implementation of App Widget functionality.
 */
class mAppWidget : AppWidgetProvider() {

    override fun onReceive(context: Context?, intent: Intent?) {
        super.onReceive(context, intent)
        Log.d("mAppWidget","onReceive")
        fresh(context)
    }

    private fun fresh(context: Context?) {
        val remote = RemoteViews(context?.packageName, R.layout.m_app_widget)
        doAsync {
            val data = URL("https://sslapi.hitokoto.cn/?c=f&encode=text").readText()
            uiThread {
                remote.setTextViewText(R.id.appwidget_text, data)
                Log.d("asdfs", data)
                val con = ComponentName(context, mAppWidget::class.java)
                val appWidgetManager = AppWidgetManager.getInstance(context)
                appWidgetManager.updateAppWidget(con, remote)
            }
        }
    }

    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {
        // There may be multiple widgets active, so update all of them
        super.onUpdate(context, appWidgetManager, appWidgetIds)
        Log.d("mAppWidget","onUpdate")
//        val intent  = Intent("android.appwidget.action.APPWIDGET_UPDATE")
//        val pendingIntent = PendingIntent.getBroadcast(context,0,intent,PendingIntent.FLAG_UPDATE_CURRENT)
//        val remote = RemoteViews(context?.packageName, R.layout.m_app_widget)
//        remote.setOnClickPendingIntent(R.id.appwidget_text,pendingIntent)
//        fresh(context)
    }

    override fun onEnabled(context: Context) {
        // Enter relevant functionality for when the first widget is created
        context.startService(Intent(context, mAppwidgeService::class.java))
        Log.d("mAppWidget","onEnabled")

    }

    override fun onDeleted(context: Context?, appWidgetIds: IntArray?) {
        super.onDeleted(context, appWidgetIds)
        context?.stopService(Intent(context, mAppwidgeService::class.java))
    }
    override fun onDisabled(context: Context) {
        // Enter relevant functionality for when the last widget is disabled
        context.stopService(Intent(context, mAppwidgeService::class.java))
        Log.d("mAppWidget","onDisabled")

    }

    companion object {
        internal fun updateAppWidget(context: Context, appWidgetManager: AppWidgetManager,
                                     appWidgetId: Int) {
            val remote =RemoteViews(context?.packageName, R.layout.m_app_widget)
            appWidgetManager.updateAppWidget(appWidgetId, remote)
        }
    }
}

