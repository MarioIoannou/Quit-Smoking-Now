package com.example.quitsmoking.Services

import android.annotation.SuppressLint
import android.app.*
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
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
class ComparisonService : Service() {

    private val MY_ACTION_MAIN = "MY_ACTION_MAIN"
    private val MY_ACTION = "MY_ACTION"
    private val MY_ACTION_MORE = "MY_ACTION_MORE"
    private val MY_ACTION_LESS = "MY_ACTION_LESS"
    private val MY_ACTION_EQUAL = "MY_ACTION_EQUAL"
    private val MY_ACTION_NONE = "MY_ACTION_NONE"
    private val CHANNEL_ID = "Channel ID"
    private val notificationId = 1
    private var day = 1

    override fun onBind(intent: Intent): IBinder? {
        //throw UnsupportedOperationException("Not yet implemented")
        return null
    }

    @SuppressLint("ObsoleteSdkInt")
    override fun onCreate() {
        super.onCreate()
        Log.e("Service  onCreate", "Service Started")

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

    private fun saveSmokeData(key: String, value: Int, context: Context) {
        val sharedPreferences: SharedPreferences =
            context.getSharedPreferences("sharedPrefsSmoke", Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.apply {
            putInt(key, value)
        }.apply()
    }

    private fun saveDataDay(key: String, value: Int, context: Context) {
        val sharedPreferences: SharedPreferences =
            context.getSharedPreferences("sharedPrefsDay", Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.apply {
            putInt(key, value)
        }.apply()
    }

    private fun loadDataDay(): Int {
        val sharedPreferences: SharedPreferences =
            this.getSharedPreferences("sharedPrefsDay", Context.MODE_PRIVATE)
        val savedDayString = sharedPreferences.getInt("Day", 1)
        return savedDayString
    }

    private fun saveData(key: String, value: String, context: Context) {
        val sharedPreferences: SharedPreferences =
            context.getSharedPreferences("sharedPrefsData", Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.apply {
            putString(key, value)
        }.apply()
    }

    /*private fun loadData(): String? {
        val sharedPreferences: SharedPreferences =
            this.getSharedPreferences("sharedPrefsData", Context.MODE_PRIVATE)
        val savedString = sharedPreferences.getString("Day", null)
        return savedString
    }*/

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        Log.e("Service  onCreate", "onStartCommand Started")
        val dbInstance = CigaretteDatabase.getDatabase(this)
        var todayCigarettes = 0
        var yesterdayCigarettes = 0
        day = loadDataDay()
        GlobalScope.launch {
            todayCigarettes = dbInstance.cigaretteDao().getTodayCigarettes()
            yesterdayCigarettes = dbInstance.cigaretteDao().getYesterdayCigarettes()
            when {
                todayCigarettes > yesterdayCigarettes -> {
                    if (day > 1) {
                        saveData("Number", "More", applicationContext)
                    } else {
                        saveData("Number", "FirstTimer", applicationContext)
                        day = 2
                        saveDataDay("Day", day, applicationContext)
                    }
                }
                todayCigarettes < yesterdayCigarettes -> {
                    saveData("Number", "Less", applicationContext)
                }
                todayCigarettes == yesterdayCigarettes -> {
                    saveData("Number", "Equal", applicationContext)
                }
                todayCigarettes == 0 -> {
                    saveData("Number", "None", applicationContext)
                }
            }
        }


    saveSmokeData("Tsigaro", 1, applicationContext)
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

    with(NotificationManagerCompat.from(this))
    {
        notify(notificationId, builder.build())
    }
    return START_NOT_STICKY
}
}