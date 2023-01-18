package com.idt.test_service

import android.app.ActivityManager
import android.content.ComponentName
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.e("MainActivity", "onCreate MainActivity ")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (!foregroundServiceRunning()) {
            val serviceIntent = Intent(this, SimpleService::class.java)
            startForegroundService(serviceIntent)
        }
        test()
    }

    fun foregroundServiceRunning(): Boolean {
        val activityManager = getSystemService(ACTIVITY_SERVICE) as ActivityManager
        for (service in activityManager.getRunningServices(Int.MAX_VALUE)) {
            if (SimpleService::class.java.name == service.service.className) {
                return true
            }
        }
        return false
    }

    fun test(){
        var component = ComponentName(
            "com.idt.test_service",
            "com.idt.test_service.SimpleService",
        )
    }

}