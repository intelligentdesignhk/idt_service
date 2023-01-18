package com.idt.test_service

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Binder
import android.os.Handler
import android.os.IBinder
import android.os.Messenger
import android.util.Log


class SimpleService : Service() {
    inner class LocalBinder : Binder() {
        fun getService(): SimpleService = this@SimpleService
    }

    private lateinit var handler: Handler
    private lateinit var messenger: Messenger
    private val binder = LocalBinder()


    companion object {
        const val TAG = "SimpleService"
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.e("SimpleService", "onStartCommand SimpleService")
        createNotificationChannel()
        Thread {
            while (true) {
                Log.e("Service", "SimpleService is running...")
                try {
                    Thread.sleep(2000)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }
        }.start()
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onCreate() {
        super.onCreate()
        Log.e("SimpleService", "onCreate SimpleService")
    }

    override fun onBind(intent: Intent): IBinder {
        Log.e("SimpleService", "onBind SimpleService")
        return binder
    }

    override fun onDestroy() {
        Log.e("SimpleService", "onDestroy SimpleService")
        super.onDestroy()
    }

    override fun onUnbind(intent: Intent?): Boolean {
        Log.e("SimpleService", "onUnbind SimpleService")
        return super.onUnbind(intent)
    }

    override fun onRebind(intent: Intent?) {
        Log.e("SimpleService", "onRebind SimpleService")
        super.onRebind(intent)
    }

    fun createNotificationChannel(){
        Log.e("SimpleService", "createNotificationChannel SimpleService")
        var mNotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        var id = "my_channel_01"
        var name = "$id-name"
        var description = "$id-description"
        var importance = NotificationManager.IMPORTANCE_HIGH
        var mChannel = NotificationChannel(id,name,importance)
        mChannel.description = description
        mChannel.enableLights(true)
        mChannel.lightColor = Color.RED
        mChannel.enableVibration(true)
        mChannel.vibrationPattern = longArrayOf(100, 200, 300, 400, 500, 400, 300, 200, 400)
        mNotificationManager.createNotificationChannel(mChannel)

        var notifyID =1;
        var Channel_ID = "my_channel_01"
        val notification = Notification.Builder(this, Channel_ID)
            .setOngoing(false)
            .setContentText("Service is running")
            .setContentTitle("Service enabled")
            .setSmallIcon(R.drawable.ic_launcher_background)
        startForeground(1,notification.build())

    }
}