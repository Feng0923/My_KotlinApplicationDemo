package com.example.administrator.my_kotlinapplication.TestDemo


import android.app.Service
import android.content.*
import android.media.MediaPlayer
import kotlinx.android.synthetic.main.activity_main.*
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import com.example.administrator.my_kotlinapplication.R
import kotlinx.android.synthetic.main.app_layout.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import org.json.JSONObject
import org.jsoup.Jsoup
import rx.Observable
import rx.Observer
import rx.Subscriber
import java.net.URL



class MainActivity : AppCompatActivity() {

    private val items = listOf(
            "Mon 6/23 - Sunny - 31/17",
            "Tue 6/24 - Foggy - 21/8",
            "Wed 6/25 - Cloudy - 22/17",
            "Thurs 6/26 - Rainy - 18/11",
            "Fri 6/27 - Foggy - 21/10",
            "Sat 6/28 - TRAPPED IN WEATHERSTATION - 23/18",
            "Sun 6/29 - Sunny - 20/7"
    )

    var binder: MyService.MyBinder? = null
    private val connection: ServiceConnection = object : ServiceConnection {
        /**
         * 当Activity与Service断开连接时回调该方法
         */
        override fun onServiceDisconnected(name: ComponentName?) {
            println("--disconnected--")

        }

        /**
         * 当Activity与Service连接成功时回调该方法
         */
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            println("--connected--")
            binder = service as MyService.MyBinder? //获取service的onBind方法所返回的MyBinder对象
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.app_layout)

//       async {
//           val text = URL("https://www.baidu.com").readText()
//           uiThread { show.text = text }
//       }

        playMusic()
//        playMusicInService()
//        bindServiceTest()
//        sendBroadcastTest()

//        observerTest()
//          recyclerViewTest()
//        startService(intent)//启动service
//        startService(intent)//启动service
//        stopService(intent)//停止service
    }

    private fun recyclerViewTest() {
        val items = arrayListOf<Item>()
        this.items.forEach { val item = Item("梁", it);items.add(item) }
        val adapter = Adapter_Forecast(this, items)
        forecast_list.itemAnimator = DefaultItemAnimator()
        forecast_list.layoutManager = LinearLayoutManager(this)
//        forecast_list.layoutManager = GridLayoutManager(this,2)
        forecast_list.adapter = adapter
        adapter.setOnItemClickListener(object : Adapter_Forecast.OnItemClickListener {
            override fun onItemClick(view: View, position: Int) {
                toast("click item$position")
                adapter.addItem(0)
            }

            override fun onItemLongClick(view: View, position: Int) {
                adapter.remove(0)
            }
        })
    }

    private fun observerTest() {

        val observer = object : Observer<String>{
            override fun onNext(t: String?) {
                println("Item: $t")
            }

            override fun onError(e: Throwable?) {
                println("Error")

            }

            override fun onCompleted() {
                println("completed")
            }

        }
       val observable = Observable.create(object : Observable.OnSubscribe<String> {
           override fun call(t: Subscriber<in String>?) {
               t?.onNext("Hello")
               t?.onNext("Hi")
               t?.onNext("Aloha")
               t?.onCompleted()
           }
       })
        val observable1 = Observable.just("Hello", "Hi", "Aloha")
        observable.subscribe(observer)
    }

    var intent1: Intent? = null
    private fun playMusicInService() {
        intent1 = Intent(applicationContext, MusicService::class.java)
        startService(intent1)
        println("asdfasdfasdf")
        val intent = Intent("com.example.music")

//        println(song)
        btn_play.setOnClickListener {
            val song = et_song.text.toString()
            intent.putExtra("song",song)
            intent.putExtra("control",1)
            sendBroadcast(intent) }
        btn_stop.setOnClickListener { intent.putExtra("control",2);sendBroadcast(intent) }
    }

    private fun playMusic() {

        val mPlayer = MediaPlayer()
//        val url = URL("http://ting.baidu.com/data/music/links?songIds={247911654}")
//        val uri = Uri("http://ting.baidu.com/data/music/links?songIds={247911654}")
        val play = findViewById(R.id.btn_play)
        play.setOnClickListener {
            val song = et_song.text.toString()
            mPlayer.reset()
//            val uri = Uri.parse("http://zhangmenshiting.qianqian.com/data2/music/247912201/2479116541515704461128.mp3?xcode=d7e6285ed0867364f4316fffb8ff8771")
//            mPlayer.setDataSource(this,uri)
//            mPlayer.prepare()
//            mPlayer.start()
//            http://musicmini.baidu.com/app/search/searchList.php?qword={%E6%B5%B7%E9%98%94%E5%A4%A9%E7%A9%BA}&ie=utf-8&page={1}
            var name = song
            doAsync {
//                val document = Jsoup.connect("http://musicmini.baidu.com/app/search/searchList.php?qword={$encode}&ie=utf-8&page={1}").get()
                val document = Jsoup.connect("http://musicmini.baidu.com/app/search/searchList.php")
                        .data("qword","$name")
                        .data("ie","utf-8")
                        .data("page","1")
                        .get()
                val element = document.select("div.box").first()
                val select = element.select("a[onclick]")
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
                    mPlayer.setDataSource(song)
                    mPlayer.prepareAsync()
                    mPlayer.setOnPreparedListener { mPlayer.start() }
                }
            }


        }
        val stop = findViewById(R.id.btn_stop)
        stop.setOnClickListener { mPlayer.stop() }

    }

    private fun sendBroadcastTest(){
        var button = findViewById(R.id.btn_sendBroadcast)
        button.setOnClickListener {
            val intent: Intent = Intent()
            intent.action = "com.example.Veng.MyBroadcast" //与接收器设置的一样
            intent.putExtra("msg","Hello")
            sendBroadcast(intent) //发送广播
        }
        //动态注册
//        val myReceiver= MyReceiver()
//        val intentFilter = IntentFilter()
//        intentFilter.addAction("com.example.Veng.MyBroadcast")
//        registerReceiver(myReceiver,intentFilter)
    }

    private fun bindServiceTest() {
        val intent = Intent(this, MyService::class.java)
        bindService(intent, connection, Service.BIND_AUTO_CREATE)
        //1.intent: Service 开启的service 2.connection: ServiceConnetion 监听访问者和Service 3.flag:Boolean 是否自动创建Service 0 则不自动创建,BIND_AUTO_CREATE自动创建
        getState.setOnClickListener { toast("service的count值:${binder?.getCount()}") }
    }

    override fun onDestroy() {
        super.onDestroy()
//        unbindService(connection)
//        stopService(intent1)
    }


    /*
        class DownTask(val context: Context) : AsyncTask<URL, Int, String?>() { //启动任务前,中,和完成时的参数类型
            var hasRead: Int = 0
            lateinit var pro: ProgressDialog
            override fun onPreExecute() { //启动任务前的准备 完成一些 初始化工作 比如 界面显示 进度条
    //            super.onPreExecute()
                pro = ProgressDialog(context)
                pro.setTitle("任务正在执行中")
                pro.setMessage("请稍等....")
                pro.max = 100
                pro.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL)
                pro.isIndeterminate = false
                pro.show()
            }

            override fun onProgressUpdate(vararg values: Int?) {
    //            super.onProgressUpdate(*values)
                Toast.makeText(context,"已读取${values[0]}行",Toast.LENGTH_SHORT).show()
                pro.progress = values[0]
            }
            override fun doInBackground(vararg params: URL?): String? {
                val string = StringBuffer()
                try {
                    val connect: URLConnection? = params[0]?.openConnection()
                    val br: BufferedReader = BufferedReader(InputStreamReader(connect?.getInputStream(),"utf-8"))
                    while (br.readLine()!=null) {
                            string.append(br.readLine())
                            hasRead++
                            publishProgress(hasRead)
                    }
                    return string.toString()
                }catch (e: Exception){
                    e.printStackTrace()
                }
                return null
            }

            override fun onPostExecute(result: String?) {
    //            super.onPostExecute(result)

            }

        }*/
    fun toast(message: String, time: Int = Toast.LENGTH_SHORT): Unit {
        Toast.makeText(this, message, time).show()
    }
}
