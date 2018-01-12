package com.example.administrator.my_kotlinapplication

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.Handler
import android.util.Log
import android.widget.RemoteViews
import org.jetbrains.anko.async
import org.jetbrains.anko.uiThread
import java.net.URL
import kotlin.math.log

/**
 * Implementation of App Widget functionality.
 */
class mAppWidget : AppWidgetProvider() {

    override fun onReceive(context: Context?, intent: Intent?) {
        super.onReceive(context, intent)
            val remote = RemoteViews(context?.packageName,R.layout.m_app_widget)
            async { val data = URL("https://sslapi.hitokoto.cn/?c=f&encode=text").readText()
                uiThread {  remote.setTextViewText(R.id.appwidget_text,data)
                    Log.d("asdfs",data)
                    val con = ComponentName(context,mAppWidget::class.java)
                    val appWidgetManager = AppWidgetManager.getInstance(context)
                    appWidgetManager.updateAppWidget(con,remote)
                }
            }
    }
    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {
        // There may be multiple widgets active, so update all of them
        super.onUpdate(context, appWidgetManager, appWidgetIds)
//        val remote =RemoteViews(context?.packageName, R.layout.m_app_widget)
//        async {
//            val data = URL("https://sslapi.hitokoto.cn/?&encode=text").readText()
//
//            uiThread {  remote.setTextViewText(R.id.appwidget_text,data)
//                        Log.d("asdfs",data)
//                        val con = ComponentName(context,mAppWidget::class.java)
//                        appWidgetManager.updateAppWidget(con,remote)
//                }
//        }
        // Construct the RemoteViews object
        // Instruct the widget manager to update the widget
    }

    override fun onEnabled(context: Context) {
        // Enter relevant functionality for when the first widget is created
        context.startService(Intent(context,mAppwidgeService::class.java))
    }

    override fun onDisabled(context: Context) {
        // Enter relevant functionality for when the last widget is disabled
        context.stopService(Intent(context,mAppwidgeService::class.java))
    }

    companion object {

        internal fun updateAppWidget(context: Context, appWidgetManager: AppWidgetManager,
                                     appWidgetId: Int) {
            val remote =RemoteViews(context?.packageName, R.layout.m_app_widget)
            appWidgetManager.updateAppWidget(appWidgetId, remote)
        }
    }
}

