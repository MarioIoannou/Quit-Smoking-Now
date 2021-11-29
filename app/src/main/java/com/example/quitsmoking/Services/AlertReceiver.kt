package com.example.quitsmoking.Services

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import kotlinx.coroutines.DelicateCoroutinesApi

@DelicateCoroutinesApi
class AlertReceiver: BroadcastReceiver() {


    @SuppressLint("UnsafeProtectedBroadcastReceiver", "ObsoleteSdkInt")
    override fun onReceive(context: Context?, intent: Intent?) {
        //schedulePushNotifications()
        Log.e("Alarm","Alarm Triggered")
        val intentService = Intent(context, ComparisonService::class.java)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            context!!.startForegroundService(intentService)
        }
        else {
            context!!.startService(intentService)
        }
    }
}