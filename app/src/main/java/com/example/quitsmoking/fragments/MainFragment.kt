package com.example.quitsmoking.fragments

import android.app.*
import android.content.*
import android.graphics.BitmapFactory
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
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.example.quitsmoking.*
import com.example.quitsmoking.R
import com.example.quitsmoking.data.Cigarette
import com.example.quitsmoking.data.CigaretteDao
import com.example.quitsmoking.data.CigaretteDatabase
import com.google.android.gms.ads.*
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*

@DelicateCoroutinesApi
class MainFragment : Fragment(), View.OnClickListener {

    @RequiresApi(Build.VERSION_CODES.O)
    var receiver: BroadcastReceiver? = null
    //private lateinit var mCigaretteViewModel: CigaretteViewModel
    private lateinit var pendingIntent: PendingIntent

    //AdMob
    lateinit var mAdView : AdView

    //Alarm
    private var HOUR_TO_SHOW_PUSH = 23
    private var MINUTE_TO_SHOW_PUSH = 28

    //Notification
    private val CHANNEL_ID = "Channel ID"
    private val notificationId = 1

    //General
    private var timesSmoked: Int = 1
    private var mNotified = false
    var line: String? = null
    lateinit var sharedPreferences: SharedPreferences
    val myPreferences = "mypref"
    val timekey = "timessmoked"
    var days = 1

    private val broadCastReceiver = object : BroadcastReceiver() {
        override fun onReceive(contxt: Context?, intent: Intent?) {
            Log.e("onReceive MainFragment","Triggered")
            if (intent?.action == "MY_ACTION_MAIN"){
                timesSmoked = intent.getIntExtra("Number",1)
                println("The a has value $timesSmoked")
                saveData("Tsigaro", timesSmoked)
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: View = inflater.inflate(R.layout.fragment_main, container, false)

        val cig_btn: ImageButton = view.findViewById(R.id.CigaretteButton)
        val sub_btn: Button = view.findViewById(R.id.subtractCig)
        val txt: TextView = view.findViewById(R.id.DidYouSmokeText)
        timesSmoked = loadData()
        days = loadData()
        val time:Long = Date().time
        println("Time $time")
        /*if (days == 1) {
            //transaction(days)
            val intent1 = Intent(activity, ResultActivity::class.java)
            intent1.putExtra("first", 1)
            startActivity(intent1)
            println("1st print: $days")
            days++
        }*/

        cig_btn.setOnClickListener {
            txt.text = timesSmoked.toString()
            txt.textSize = 100F
            txt.setTextColor(Color.RED)
            val scaledown2: Animation =
                AnimationUtils.loadAnimation(activity?.baseContext, R.anim.scale_down)
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
                    timesSmoked++
                }
            }
            saveData("Tsigaro", timesSmoked)
        }
        /*val week = Calendar.getInstance(TimeZone.getTimeZone("UTC")).get(Calendar.WEEK_OF_YEAR)
        println("Week : $week")*/

        /*if (LocalTime.now() in LocalTime.of(21, 0, 0)..LocalTime.of(21, 0, 30)) {
            sendNotification()
        }*/
        saveData("Days", days)
        sub_btn.setOnClickListener {
            deleteCigaretteData()
            saveData("Tsigaro", timesSmoked)
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

    override fun onResume() {
        super.onResume()
        LocalBroadcastManager.getInstance(requireActivity()).registerReceiver(broadCastReceiver,
            IntentFilter("MY_ACTION"))
    }

    override fun onPause() {
        super.onPause()
        LocalBroadcastManager.getInstance(requireActivity()).unregisterReceiver(broadCastReceiver)
    }

//--------------- SharedPreferences -------------//

    private fun saveData(key: String, value: Int) {
        val sharedPreferences: SharedPreferences =
            requireActivity().getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.apply {
            putInt(key, value)
        }.apply()
    }

    private fun loadData(): Int {
        val sharedPreferences: SharedPreferences =
            requireActivity().getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
        val savedString = sharedPreferences.getInt("Tsigaro", 1)
        return savedString
    }

    @DelicateCoroutinesApi
    fun addCigaretteData() {
        val dbInstance = CigaretteDatabase.getDatabase(requireContext())
        val tsigaro = Cigarette(timesSmoked, Date()) as Cigarette
        GlobalScope.launch {
            dbInstance.cigaretteDao().addCigarette(tsigaro)
            val tsigara = dbInstance.cigaretteDao().getAll()
            Log.e("Student", "$tsigara")
        }
    }

    @DelicateCoroutinesApi
    fun deleteCigaretteData() {
        val dbInstance = CigaretteDatabase.getDatabase(requireContext())
        val tsigaro = Cigarette(timesSmoked, Date()) as Cigarette
        GlobalScope.launch {
            dbInstance.cigaretteDao().deleteCigarette(tsigaro)
            val tsigara = dbInstance.cigaretteDao().getAll()
            Log.e("Student", "$tsigara")
        }
    }

    /*fun schedulePushNotifications() {
        val calendar = GregorianCalendar.getInstance().apply {
            if (get(Calendar.HOUR_OF_DAY) >= HOUR_TO_SHOW_PUSH) {
                add(Calendar.DAY_OF_MONTH, 1)
            }

            set(Calendar.HOUR_OF_DAY, HOUR_TO_SHOW_PUSH)
            set(Calendar.MINUTE, 0)
            //set(Calendar.MINUTE, MINUTE_TO_SHOW_PUSH)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }

        alarmManager?.setRepeating(
            AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
            AlarmManager.INTERVAL_DAY,
            alarmIntent
        )
        Log.e("PushNotifications","Broadcast Sent")
    }*/

    //----------------- It works AppWidget --------------------------------------------------

    /*private fun initReceiver() {
        val receiver = QuitSmokingNowWidget()
        val intent = Intent()
        val filter = IntentFilter(Intent.ACTION_POWER_DISCONNECTED)
        filter.addAction("android.appwidget.action.APPWIDGET_UPDATE")
        requireActivity().registerReceiver(receiver, filter)
    }

    fun sendIntent(value: Int) {
        val intent = Intent("android.appwidget.action.APPWIDGET_UPDATE")
        intent.action = AppWidgetManager.ACTION_APPWIDGET_UPDATE
        intent.putExtra("smokes", value)
        requireContext().sendBroadcast(intent)
    }*/

    //------------------------------------------------------------------------------

    private fun createNotificationChannel() {
        val name = "Notification Title"
        val descriptionText = "Notification Description"
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
            description = descriptionText
        }
        val notificationManager: NotificationManager =
            activity?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }
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

    private fun sendNotification() {
        val icon = BitmapFactory.decodeResource(resources, R.drawable.launcher_logo_2)
        val icon2 = BitmapFactory.decodeResource(resources, R.drawable.quitsmokingnowtext)
        val intent = Intent(requireContext(), MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent: PendingIntent = PendingIntent.getActivity(requireContext(), 0, intent, 0)
        val builder = NotificationCompat.Builder(requireContext(), CHANNEL_ID)
            .setSmallIcon(R.drawable.launcher_logo_2)
            .setContentTitle("Quit Smoking Now")
            .setContentText("The results are ready for you to see. Please view them!")
            .setLargeIcon(icon)
            .setContentIntent(pendingIntent)
            .setStyle(NotificationCompat.BigPictureStyle().bigPicture(icon2))
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        with(NotificationManagerCompat.from(requireContext())) {
            notify(notificationId, builder.build())
        }
    }

    /*fun clearData(view: View){
        DidYouSmokeText.text = ""
    }*/

    /*private fun loadBannerAd(){
        MobileAds.initialize(requireContext()) {}
        mAdView = requireView().findViewById(R.id.adViewMain)
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)
        mAdView.adListener = object: AdListener() {
            override fun onAdLoaded() {
                // Code to be executed when an ad finishes loading.
                Log.e("onAdLoaded - Main", "Ad Loaded")
            }

            override fun onAdFailedToLoad(adError: LoadAdError) {
                // Code to be executed when an ad request fails.
                Log.e("onAdFailedToLoad - Main", "Ad Failed To Load")
            }

            override fun onAdOpened() {
                // Code to be executed when an ad opens an overlay that
                // covers the screen.
                Log.e("onAdOpened - Main", "Ad Opened")
            }

            override fun onAdClicked() {
                // Code to be executed when the user clicks on an ad.
                Log.e("onAdClicked - Main", "Ad Clicked")
            }

            override fun onAdClosed() {
                // Code to be executed when the user is about to return
                // to the app after tapping on an ad.
                Log.e("onAdClosed - Main", "Ad Closed")
            }
        }
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
