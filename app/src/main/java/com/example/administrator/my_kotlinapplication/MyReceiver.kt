package com.example.administrator.my_kotlinapplication

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

/**
 * Created by Administrator on 2018/1/12/012.
 */
class MyReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        Toast.makeText(context,"received action is ${intent?.action},message is ${intent?.getStringExtra("msg")}",Toast.LENGTH_LONG).show()
    }
}