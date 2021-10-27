package com.example.quitsmoking

import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContentProviderCompat.requireContext

class AlertReceiver: BroadcastReceiver() {

    private val CHANNEL_ID = "Channel ID"
    private val notificationId = 1

    override fun onReceive(context: Context?, intent: Intent?) {

        val service = Intent(context, NotificationService::class.java)
        service.putExtra("reason", intent?.getStringExtra("reason"))
        service.putExtra("timestamp", intent?.getLongExtra("timestamp", 0))

        context?.startService(service)
    }
}
/*
val i = Intent(context,ResultActivity::class.java)
        intent!!.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        val pendingIntent = PendingIntent.getActivity(context,0,i,0)
        val builder = NotificationCompat.Builder(context!!,CHANNEL_ID)
            .setSmallIcon(R.drawable.launcher_logo_2)
            .setContentTitle("Quit Smoking Now")
            .setContentText("The results are ready for you to see. Please view them!")
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .setDefaults(NotificationCompat.DEFAULT_ALL)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        val notificationManager = context.let { NotificationManagerCompat.from(it) }
        notificationManager.notify(notificationId,builder.build())
        */