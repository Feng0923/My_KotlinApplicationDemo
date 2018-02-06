package com.example.administrator.my_kotlinapplication

import android.app.Service
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.media.MediaPlayer
import android.net.Uri
import android.os.Binder
import android.os.IBinder
import org.jetbrains.anko.custom.async
import org.jetbrains.anko.uiThread
import org.json.JSONObject
import org.jsoup.Jsoup
import java.net.URL

/**
 * Created by Administrator on 2018/1/12/012.
 */
class MusicService : Service() {
    //0x11
    var state = 0x11
    var song: String = "断桥残雪"
    private var mPlayer: MediaPlayer? = null
    val serviceReceiver = object : BroadcastReceiver(){
        override fun onReceive(context: Context?, intent: Intent?) {
            println("aasdfads-------a")
            val control = intent?.getIntExtra("control",-1)
            when(control){
                1-> {
                    when(state){
                        0x11-> {val song = intent?.getStringExtra("song");prepareAndPlay(song);state = 0x12}
                        0x12-> {mPlayer?.pause();state = 0x13}
                        0x13-> {mPlayer?.start();state = 0x12}
                    }
                }
                2->when(state){
                    0x12,0x13->{mPlayer?.stop(); state = 0x11}
                }
            }
        }
    }

    private fun prepareAndPlay(song: String?) {
        println("15646456423123121")
        var name = song
        println(song)
        async {
            //                val document = Jsoup.connect("http://musicmini.baidu.com/app/search/searchList.php?qword={$encode}&ie=utf-8&page={1}").get()
            val document = Jsoup.connect("http://musicmini.baidu.com/app/search/searchList.php")
                    .data("qword","$name")
                    .data("ie","utf-8")
                    .data("page","1")
                    .get()
            val element = document.select("div.box").first()
            val select = element.select("a")
            val attr = select.attr("onclick")
            val gets = attr.split("'")
            val songId = gets[1]
            println(gets)
            val url = URL("http://music.baidu.com/data/music/links?songIds={$songId}")
            val json = url.readText()
            uiThread {
                val jsonObject: JSONObject = JSONObject(json).getJSONObject("data").getJSONArray("songList")[0] as JSONObject
                val song: String = jsonObject.getString("songLink")
//                    println(song)
//                    println(jsonObject.toString())
                    mPlayer?.setDataSource(song)
//            val uri = Uri.parse(song)
//            mPlayer?.setDataSource(applicationContext,uri)
//            mPlayer?.prepare()
//            mPlayer?.start()
                    mPlayer?.prepareAsync()
                    mPlayer?.setOnPreparedListener { mPlayer?.start() }
            }
        }
    }
    private val binder = MyBinder()
    inner class MyBinder : Binder() {

    }

    override fun onBind(intent: Intent?): IBinder {
        return binder
    }


    override fun onCreate() {
        super.onCreate()
        mPlayer = MediaPlayer()
        val intentFilter = IntentFilter()
        intentFilter.addAction("com.example.music")
        registerReceiver(serviceReceiver,intentFilter)
    }
}