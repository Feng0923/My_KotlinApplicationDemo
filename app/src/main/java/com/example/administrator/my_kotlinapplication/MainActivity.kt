package com.example.administrator.my_kotlinapplication


import android.content.IntentFilter
import android.os.AsyncTask
import kotlinx.android.synthetic.main.activity_main.*
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.app_layout)
       async {
           val text = URL("https://sslapi.hitokoto.cn/?c=f&encode=text").readText()
           uiThread { show.text = text }
       }

//        var i: Int = 0
//        btn.setOnClickListener { toast("click ${i++}") }
//        toast("哈哈!")
//        forecast_list.layoutManager = LinearLayoutManager(this)
//        forecast_list.adapter = Adapter_Forecast(items)
//        variableTest()

//        val request = Request("http://www.baidu.com")
//        request.run()
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
     fun variableTest() {
        val s = "example"
        val c = s[2] // 这是一个字符'a'
        // 迭代string
        for (c in s) {
            print(c)
        }
    }

    fun toast(message: String, time: Int = Toast.LENGTH_SHORT): Unit {
        Toast.makeText(this,message,time).show()
    }
}
