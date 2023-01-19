package com.idt.test_service

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class MyBroadcastReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val TAG = "MyBroadcastReceiver"
        val action = intent!!.action
        if (action == Intent.ACTION_BOOT_COMPLETED) {
            Log.e("MyBroadcastReceiver", "start serviceIntent")
            val serviceIntent = Intent(context, SimpleService::class.java)
            Log.e("MyBroadcastReceiver", "mid serviceIntent")
            context!!.startForegroundService(serviceIntent)
            Log.e("MyBroadcastReceiver", "end serviceIntent")
        }else{
            Log.e(TAG, "onReceive: $action" )
        }
    }
}