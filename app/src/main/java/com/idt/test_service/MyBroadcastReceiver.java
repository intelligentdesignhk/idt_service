package com.idt.test_service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MyBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)) {
            Log.e("MyBroadcastReceiver", "start serviceIntent");
            Intent serviceIntent = new Intent(context, SimpleService.class);
            Log.e("MyBroadcastReceiver", "mid serviceIntent");
            context.startForegroundService(serviceIntent);
            Log.e("MyBroadcastReceiver", "end serviceIntent");

        }
    }
}
