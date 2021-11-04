package com.example.quitsmoking

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.quitsmoking.widget.QuitSmokingNowWidget

class MyReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val intent = Intent(context, QuitSmokingNowWidget::class.java)
        intent.putExtra("nameValue", "smokes")
    }
}