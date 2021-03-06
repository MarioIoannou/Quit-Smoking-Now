package com.helpapp.quitsmoking.AlarmManager

import android.annotation.SuppressLint
import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import android.widget.Toast
import com.helpapp.quitsmoking.R
import com.helpapp.quitsmoking.ResultActivity
import com.helpapp.quitsmoking.data.CigaretteDatabase
import com.helpapp.quitsmoking.fragments.MainFragment
import kotlinx.coroutines.*
import java.util.*

class NotificationService: IntentService("NotificationService") {

    private lateinit var mNotification: Notification
    private val mNotificationId: Int = 1000

    @SuppressLint("NewApi")
    private fun createChannel() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Create the NotificationChannel, but only on API 26+ because
            // the NotificationChannel class is new and not in the support library
            val context = this.applicationContext
            val notificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            val importance = NotificationManager.IMPORTANCE_HIGH
            val notificationChannel = NotificationChannel(
                com.helpapp.quitsmoking.AlarmManager.NotificationService.Companion.CHANNEL_ID,
                com.helpapp.quitsmoking.AlarmManager.NotificationService.Companion.CHANNEL_NAME, importance)
            notificationChannel.enableVibration(true)
            notificationChannel.setShowBadge(true)
            notificationChannel.enableLights(true)
            notificationChannel.lightColor = Color.parseColor("#e8334a")
            notificationChannel.description = getString(R.string.notification_channel_description)
            notificationChannel.lockscreenVisibility = Notification.VISIBILITY_PUBLIC
            notificationManager.createNotificationChannel(notificationChannel)
        }
    }

    companion object {
        const val CHANNEL_ID = "samples.notification.devdeeds.com.CHANNEL_ID"
        const val CHANNEL_NAME = "Sample Notification"
    }

    @DelicateCoroutinesApi
    fun compareCigarettes(context: Context) {

        val dbInstance = CigaretteDatabase.getDatabase(context)
        var todayCigarettes = 0
        var yesterdayCigarettes = 0
        var result = ""
        val intentResult = Intent(this, MainFragment::class.java)
        val intentMain = Intent(this, MainFragment::class.java)

        GlobalScope.launch {
            todayCigarettes = dbInstance.cigaretteDao().getTodayCigarettes()
            yesterdayCigarettes = dbInstance.cigaretteDao().getYesterdayCigarettes()
            when {
                yesterdayCigarettes > todayCigarettes -> {
                    Intent().also { intentMain ->
                        intentMain.putExtra("Done", 1)
                        sendBroadcast(intentMain)
                        println("Put Extra: Done-Less sent")
                        intentResult.putExtra("Less", yesterdayCigarettes)
                        sendBroadcast(intentResult)
                    }
                }
                yesterdayCigarettes < todayCigarettes -> {
                    Intent().also { intentMain ->
                        intentMain.putExtra("Done", 1)
                        sendBroadcast(intentMain)
                        println("Put Extra: Done-More sent")
                        intentResult.putExtra("More", yesterdayCigarettes)
                        sendBroadcast(intentResult)
                    }
                }
                else -> ({
                    println("Nothing happened")
                }).toString()
            }
            withContext(Dispatchers.Main) {
                Toast.makeText(context, result, Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onHandleIntent(intent: Intent?) {
        //Create Channel
        createChannel()
        var timestamp: Long = 0
        if (intent != null && intent.extras != null) {
            timestamp = intent.extras!!.getLong("timestamp")
        }
        if (timestamp > 0) {

            val context = this.applicationContext
            var notificationManager: NotificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            val notifyIntent = Intent(this, ResultActivity::class.java)

            val title = "Quit Smoking Now"
            val message = "The results are ready for you to see. Please view them!"

            notifyIntent.putExtra("title", title)
            notifyIntent.putExtra("message", message)
            notifyIntent.putExtra("notification", true)
            notifyIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            val calendar = Calendar.getInstance()
            calendar.timeInMillis = timestamp
            val pendingIntent = PendingIntent.getActivity(
                context,
                0,
                notifyIntent,
                PendingIntent.FLAG_UPDATE_CURRENT
            )
            val res = this.resources

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                mNotification = Notification.Builder(this,
                    com.helpapp.quitsmoking.AlarmManager.NotificationService.Companion.CHANNEL_ID
                )
                    // Set the intent that will fire when the user taps the notification
                    .setContentIntent(pendingIntent)
                    .setSmallIcon(R.drawable.launcher_logo_2)
                    .setLargeIcon(BitmapFactory.decodeResource(res, R.drawable.launcher_logo_2))
                    .setAutoCancel(true)
                    .setContentTitle(title)
                    .setStyle(
                        Notification.BigTextStyle()
                            .bigText(message)
                    )
                    .setContentText(message).build()
            } else {
                mNotification = Notification.Builder(this)
                    // Set the intent that will fire when the user taps the notification
                    .setContentIntent(pendingIntent)
                    .setSmallIcon(R.drawable.launcher_logo_2)
                    .setLargeIcon(BitmapFactory.decodeResource(res, R.drawable.launcher_logo_2))
                    .setAutoCancel(true)
                    .setPriority(Notification.PRIORITY_MAX)
                    .setContentTitle(title)
                    .setStyle(
                        Notification.BigTextStyle()
                            .bigText(message)
                    )
                    .setContentText(message).build()
            }
            notificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            // mNotificationId is a unique int for each notification that you must define
            notificationManager.notify(mNotificationId, mNotification)
        }
    }


}