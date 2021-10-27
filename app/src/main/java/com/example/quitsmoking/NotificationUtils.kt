package com.example.quitsmoking

import android.app.Activity
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Intent
import com.example.quitsmoking.fragments.MainFragment
import java.util.*

class NotificationUtils {
    fun setNotification(timeInMilliSeconds: Calendar, activity: MainFragment) {

        //------------  alarm settings start  -----------------//

        /*if (timeInMilliSeconds > 0) {

            val alarmManager = activity.getSystemService(Activity.ALARM_SERVICE) as AlarmManager
            val alarmIntent = Intent(activity.applicationContext, AlertReceiver::class.java) // AlarmReceiver1 = broadcast receiver
            alarmIntent.putExtra("reason", "notification")
            alarmIntent.putExtra("timestamp", timeInMilliSeconds)
            val calendar = Calendar.getInstance()
            calendar.timeInMillis = timeInMilliSeconds
            val pendingIntent = PendingIntent.getBroadcast(activity, 0, alarmIntent, PendingIntent.FLAG_CANCEL_CURRENT)
            alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, pendingIntent)
        }*/

        //------------ end of alarm settings  -----------------//

    }
}