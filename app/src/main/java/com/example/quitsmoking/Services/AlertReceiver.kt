package com.example.quitsmoking.Services

import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContentProviderCompat.requireContext
import com.example.quitsmoking.MainActivity
import com.example.quitsmoking.R
import com.example.quitsmoking.fragments.MainFragment

class AlertReceiver: BroadcastReceiver() {

    private val CHANNEL_ID = "Channel ID"
    private val notificationId = 1

    override fun onReceive(context: Context?, intent: Intent?) {

        //val icon = BitmapFactory.decodeResource(resources, R.drawable.launcher_logo_2)
        //val icon2 = BitmapFactory.decodeResource(resources, R.drawable.quitsmokingnowtext)
        val intentEntry = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        //val i = Intent(context,MainFragment::class.java)
        intent!!.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        val pendingIntent = PendingIntent.getActivity(context,0,intentEntry,0)
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
        val service = Intent(context, NotificationService::class.java)
        service.putExtra("reason", intent.getStringExtra("reason"))
        service.putExtra("timestamp", intent.getLongExtra("timestamp", 0))

        context.startService(service)

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

/*rivate fun sendNotification(){
        val icon = BitmapFactory.decodeResource(resources, R.drawable.launcher_logo_2)
        val icon2 = BitmapFactory.decodeResource(resources, R.drawable.quitsmokingnowtext)
        val intent = Intent(requireContext(),MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent: PendingIntent = PendingIntent.getActivity(requireContext(),0,intent,0)
        val builder = NotificationCompat.Builder(requireContext(),CHANNEL_ID)
            .setSmallIcon(R.drawable.launcher_logo_2)
            .setContentTitle("Quit Smoking Now")
            .setContentText("The results are ready for you to see. Please view them!")
            .setLargeIcon(icon)
            .setContentIntent(pendingIntent)
            .setStyle(NotificationCompat.BigPictureStyle().bigPicture(icon2))
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        with(NotificationManagerCompat.from(requireContext())){
            notify(notificationId,builder.build())
        }
    }*/