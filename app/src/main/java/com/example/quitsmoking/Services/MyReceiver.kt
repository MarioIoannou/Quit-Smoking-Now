package com.example.quitsmoking.Services

import android.appwidget.AppWidgetProvider
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.quitsmoking.widget.QuitSmokingNowWidget

class MyReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val intent = Intent(context, AppWidgetProvider::class.java)
        intent.putExtra("nameValue", "smokes")
    }
}