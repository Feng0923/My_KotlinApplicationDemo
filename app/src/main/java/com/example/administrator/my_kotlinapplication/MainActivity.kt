package com.example.administrator.my_kotlinapplication


import android.app.Service
import android.content.ComponentName
import android.content.Intent
import android.content.IntentFilter
import android.content.ServiceConnection
import android.os.AsyncTask
import kotlinx.android.synthetic.main.activity_main.*
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.app_layout.*
import org.jetbrains.anko.async
import org.jetbrains.anko.uiThread
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


        bindServiceTest()
        sendBroadcastTest()
//        startService(intent)//启动service
//        startService(intent)//启动service
//        stopService(intent)//停止service
    }

    private fun sendBroadcastTest() {
        val intent = Intent()
        intent.setAction("com.example.MyBroadcast") //与接收器设置的一样
        intent.putExtra("msg","Hello")
        sendBroadcast(intent) //发送广播
    }

    private fun bindServiceTest() {
        val intent = Intent(this, MyService::class.java)
        bindService(intent, connection, Service.BIND_AUTO_CREATE)
        //1.intent: Service 开启的service 2.connection: ServiceConnetion 监听访问者和Service 3.flag:Boolean 是否自动创建Service 0 则不自动创建,BIND_AUTO_CREATE自动创建
        getState.setOnClickListener { toast("service的count值:${binder?.getCount()}") }
    }

    override fun onDestroy() {
        super.onDestroy()
        unbindService(connection)
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
