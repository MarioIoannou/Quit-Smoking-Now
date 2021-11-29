package com.example.quitsmoking.Services

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import android.os.IBinder
import android.util.Log
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.quitsmoking.MainActivity
import com.example.quitsmoking.R
import com.example.quitsmoking.data.CigaretteDatabase
import com.example.quitsmoking.fragments.MainFragment
import com.example.quitsmoking.fragments.ResultFragment
import kotlinx.coroutines.*

@DelicateCoroutinesApi
class ComparisonService: Service() {

    private val MY_ACTION_MORE = "MY_ACTION_MORE"
    private val MY_ACTION_LESS = "MY_ACTION_LESS"
    private val MY_ACTION_EQUAL = "MY_ACTION_EQUAL"
    private val MY_ACTION_NONE = "MY_ACTION_NONE"
    private val CHANNEL_ID = "Channel ID"
    private val notificationId = 1

    override fun onBind(intent: Intent): IBinder? {
        //throw UnsupportedOperationException("Not yet implemented")
        return null
    }

    @SuppressLint("ObsoleteSdkInt")
    override fun onCreate() {
        super.onCreate()
        Log.e("Service  onCreate","Service Started")

        if (Build.VERSION.SDK_INT >= 26) {
            val channel = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                NotificationChannel(
                    CHANNEL_ID,
                    "Channel human readable title",
                    NotificationManager.IMPORTANCE_DEFAULT
                )
            } else {
                TODO("VERSION.SDK_INT < O")
            }
            (getSystemService(NOTIFICATION_SERVICE) as NotificationManager).createNotificationChannel(
                channel
            )
        }
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        //createNotificationChannel()
        //sendNotification()
        val dbInstance = CigaretteDatabase.getDatabase(this)
        var todayCigarettes = 0
        var yesterdayCigarettes = 0
        val intentdata = Intent()
        sendBroadcast(intent)
        GlobalScope.launch {
            todayCigarettes = dbInstance.cigaretteDao().getTodayCigarettes()
            yesterdayCigarettes = dbInstance.cigaretteDao().getYesterdayCigarettes()
            when {
                yesterdayCigarettes > todayCigarettes -> {
                    val consumption = yesterdayCigarettes - todayCigarettes
                    intentdata.action =MY_ACTION_MORE
                    intentdata.putExtra("MoreDifference", consumption)
                    intentdata.putExtra("More", todayCigarettes)
                    sendBroadcast(intentdata)
                }
                yesterdayCigarettes == todayCigarettes -> {
                    intentdata.action =MY_ACTION_EQUAL
                    intentdata.putExtra("EqualDifference", 0)
                    intentdata.putExtra("Equal", todayCigarettes)
                    sendBroadcast(intentdata)
                }
                yesterdayCigarettes < todayCigarettes -> {
                    val consumption = yesterdayCigarettes - todayCigarettes
                    intentdata.action =MY_ACTION_LESS
                    intentdata.putExtra("LessDifference", consumption)
                    intentdata.putExtra("Less", todayCigarettes)
                    sendBroadcast(intentdata)
                }
                else -> {
                    val consumption = 0
                    intentdata.action =MY_ACTION_NONE
                    intentdata.putExtra("NoneDifference", consumption)
                    sendBroadcast(intentdata)
                }
            }
            /*withContext(Dispatchers.Main) {
                Toast.makeText(this, result, Toast.LENGTH_SHORT).show()
            }*/
        }
        val icon = BitmapFactory.decodeResource(resources, R.drawable.launcher_logo_2)
        val icon2 = BitmapFactory.decodeResource(resources, R.drawable.quitsmokingnowtext)
        val intentNot = Intent(this, MainActivity::class.java)
        val pendingIntent: PendingIntent = PendingIntent.getActivity(this, 0, intentNot, 0)
        val builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.launcher_logo_2)
            .setContentTitle("Quit Smoking Now")
            .setContentText("The results are ready for you to see. Please view them!")
            .setLargeIcon(icon)
            .setContentIntent(pendingIntent)
            .setStyle(NotificationCompat.BigPictureStyle().bigPicture(icon2))
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setAutoCancel(true)

        with(NotificationManagerCompat.from(this)) {
            notify(notificationId, builder.build())
        }
        return START_NOT_STICKY
    }
}