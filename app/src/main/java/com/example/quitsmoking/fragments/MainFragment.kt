package com.example.quitsmoking.fragments

import android.app.*
import android.appwidget.AppWidgetManager
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.media.MediaPlayer
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_main.*
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import com.example.quitsmoking.*
import com.example.quitsmoking.data.Cigarette
import com.example.quitsmoking.data.CigaretteDao
import com.example.quitsmoking.data.CigaretteDatabase
import com.example.quitsmoking.data.CigaretteViewModel
import com.example.quitsmoking.widget.QuitSmokingNowWidget
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*

class MainFragment : Fragment(), View.OnClickListener {

    @RequiresApi(Build.VERSION_CODES.O)
    //val timeNow: org.joda.time.LocalTime = org.joda.time.LocalTime.now()
    //val time : String = "01:51:00"
    //private val deadline: org.joda.time.LocalTime = org.joda.time.LocalTime("01:51:00")
    private lateinit var  mCigaretteViewModel: CigaretteViewModel
    private lateinit var pendingIntent : PendingIntent
    private var alarmManager: AlarmManager? = null
    private var timesSmoked: Int = 1
    private var mNotified = false
    var line: String? = null
    lateinit var sharedPreferences: SharedPreferences
    val myPreferences = "mypref"
    val timekey = "timessmoked"
    var days = 1
    private val CHANNEL_ID = "Channel ID"
    private val notificationId = 1
    private lateinit var dao: CigaretteDao

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater!!.inflate(R.layout.fragment_main, container, false)
        val cig_btn: ImageButton = view.findViewById(R.id.CigaretteButton)
        val sub_btn: Button = view.findViewById(R.id.subtractCig)
        val txt : TextView = view.findViewById(R.id.DidYouSmokeText)
        //mCigaretteViewModel = ViewModelProvider(this).get(CigaretteViewModel::class.java)
        alarmManager = context?.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        pendingIntent = Intent(context, AlertReceiver::class.java).let { intent ->
            PendingIntent.getBroadcast(context, 0, intent, 0)
        }
        val calendar: Calendar = Calendar.getInstance().apply {
            timeInMillis = System.currentTimeMillis()
            set(Calendar.HOUR_OF_DAY, 2)
            set(Calendar.MINUTE, 17)
        }
        var dbInstance = CigaretteDatabase.getDatabase(this)
        val tsigaro = Cigarette(timesSmoked,Date()) as Cigarette
        val currentTime = Calendar.getInstance()
        val timeToMatch = Calendar.getInstance()
        timeToMatch[Calendar.HOUR_OF_DAY] = 24
        timesSmoked = loadData()
        days = loadData()
        //if(time != deadline){
        if(currentTime != timeToMatch){
            if(days == 1){
                //transaction(days)

                val intent1 = Intent(activity, ResultActivity::class.java)
                intent1.putExtra("first",1)
                startActivity(intent1)
                println("1st print: $days")

                /*val transaction = activity?.supportFragmentManager?.beginTransaction()
                transaction?.replace(R.id.MainFrag, ResultFragment())
                transaction?.disallowAddToBackStack()
                transaction?.commit()*/
                days ++
            }
            cig_btn.setOnClickListener {
                txt.text = timesSmoked.toString()
                txt.setTextSize(100F)
                txt.setTextColor(Color.RED)
                val scaledown2: Animation = AnimationUtils.loadAnimation(activity?.baseContext, R.anim.scale_down)
                val scaleup2 = AnimationUtils.loadAnimation(activity?.baseContext, R.anim.scale_up)
                when {
                    timesSmoked == 1 -> {
                        addCigaretteData()
                        CigaretteButton?.startAnimation(scaledown2)
                        CigaretteButton?.startAnimation(scaleup2)
                        val mp2 = MediaPlayer.create(activity?.baseContext, R.raw.human_smoking)
                        mp2?.start()
                        timesSmoked++
                    }
                    timesSmoked >= 2 -> {
                        addCigaretteData()
                        CigaretteButton?.startAnimation(scaledown2)
                        CigaretteButton?.startAnimation(scaleup2)
                        val mp3 = MediaPlayer.create(activity?.baseContext, R.raw.human_smoking)
                        mp3?.start()
                        timesSmoked++
                    }
                    else -> {
                        Toast.makeText(activity?.baseContext, "ERROR 404", Toast.LENGTH_SHORT).show()
                        timesSmoked ++
                    }
                }
                saveData("Tsigaro",timesSmoked)
                println(timesSmoked)
                context?.let{
                    val componentName = ComponentName(it, QuitSmokingNowWidget::class.java)
                    val intentWidget = Intent(requireContext(), QuitSmokingNowWidget::class.java)
                    intentWidget.action = AppWidgetManager.ACTION_APPWIDGET_UPDATE
                    val ids = AppWidgetManager.getInstance(it).getAppWidgetIds(componentName)
                    intentWidget.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, ids)
                    intentWidget.putExtra("smokes",timesSmoked)
                    requireContext().sendBroadcast(intentWidget)
                }
            }

        }else{
            if (!mNotified) {
                //NotificationUtils().setNotification(timeToMatch, this@MainFragment)
                alarmManager!!.set(AlarmManager.RTC_WAKEUP,calendar.timeInMillis,pendingIntent)
            }
            if(days > 1){
                val intent2 = Intent(requireContext(), ResultActivity::class.java)
                intent2.putExtra("smokes",timesSmoked)
                //startActivity(intent2)
                days++
                Toast.makeText(activity?.baseContext, "Else...", Toast.LENGTH_SHORT).show()
            }
            timesSmoked = 1
            saveData("Tsigaro",timesSmoked)
        }
        saveData("Days",days)
        sub_btn.setOnClickListener {
            deleteCigaretteData()
            saveData("Tsigaro",timesSmoked)
            if (timesSmoked > 0) {
                timesSmoked--
                txt.text = timesSmoked.toString()
                txt.setTextSize(100F)
            } else {
                Toast.makeText(
                    activity?.baseContext,
                    "You can't subtract when you didn't smoke any cigarette.",
                    Toast.LENGTH_SHORT
                ).show()
                timesSmoked = 0
            }
        }
        return view
    }

    /*override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        for (fragment in MainActivity.fragments)
        { fragment.onActivityResult(requestCode, resultCode, data) }
    }*/

//--------------- SharedPreferences -------------//

    private fun saveData(key: String,value: Int){
        val sharedPreferences: SharedPreferences = requireActivity().getSharedPreferences ("sharedPrefs",Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.apply {
            putInt(key, value)
        }.apply()
    }

    private fun loadData(): Int {
        val sharedPreferences: SharedPreferences = requireActivity().getSharedPreferences ("sharedPrefs",Context.MODE_PRIVATE)
        val savedString = sharedPreferences.getInt("Tsigaro",1)
        return savedString
    }

    fun addCigaretteData(){
        var dbInstance = CigaretteDatabase.getDatabase(this)
        val tsigaro = Cigarette(timesSmoked,Date()) as Cigarette
        GlobalScope.launch {
            dbInstance.cigaretteDao().addCigarette(tsigaro)
            var tsigara = dbInstance.cigaretteDao().getAll()
            Log.e("Student", "$tsigara")
        }
    }

    fun deleteCigaretteData(){
        var dbInstance = CigaretteDatabase.getDatabase(this)
        val tsigaro = Cigarette(timesSmoked,Date()) as Cigarette
        GlobalScope.launch {
            dbInstance.cigaretteDao().deleteCigarette(tsigaro)
            var tsigara = dbInstance.cigaretteDao().getAll()
            Log.e("Student", "$tsigara")
        }
    }

    /*private fun createNotificationChannel(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val name = "Notification Title"
            val descriptionText = "Notification Description"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID,name,importance).apply {
                description = descriptionText
            }
            val notificationManager : NotificationManager = activity?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }*/
    //.setStyle(NotificationCompat.BigPictureStyle().bigPicture(icon))

   /* @RequiresApi(Build.VERSION_CODES.O)
    private fun onTimeSet(hourOfDay : Int, minutes: Int){
        val cal : Calendar = Calendar.getInstance()
        cal.set(Calendar.HOUR_OF_DAY,hourOfDay)
        cal.set(Calendar.MINUTE,minutes)
        cal.set(Calendar.SECOND,0)
        //startAlarm()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun startAlarm(cal : Calendar) {
        alarmManager= activity?.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(requireContext(),AlertReceiver::class.java)
        pendingIntent = PendingIntent.getBroadcast(requireContext(),0,intent,0)
        alarmManager.setExact(AlarmManager.RTC_WAKEUP,cal.timeInMillis,pendingIntent)
    }*/

    /*private fun sendNotification(){
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

    /*private fun startThread(){
        for (int i)
    }*/

    /*fun clearData(view: View){
        DidYouSmokeText.text = ""
    }*/


    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.CigaretteButton -> {

            }

            else -> {
            }
        }
    }

}
