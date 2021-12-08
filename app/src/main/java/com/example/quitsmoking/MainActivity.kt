package com.example.quitsmoking

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.DisplayMetrics
import android.widget.Toast
import com.example.quitsmoking.fragments.adapters.ViewPagerAdapter
import com.google.android.gms.ads.*
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import com.example.quitsmoking.Services.AlertReceiver
import com.example.quitsmoking.fragments.*
import kotlinx.coroutines.DelicateCoroutinesApi

lateinit var alarmManager: AlarmManager
lateinit var alarmIntent: PendingIntent
private val CHANNEL_ID = "Channel ID"
private val notificationId = 1

@DelicateCoroutinesApi
class MainActivity : AppCompatActivity() {

    lateinit var mAdView : AdView
    private val adSize: AdSize
        get() {
            val display = windowManager.defaultDisplay
            val outMetrics = DisplayMetrics()
            display.getMetrics(outMetrics)

            val density = outMetrics.density

            val adWidthPixels = outMetrics.widthPixels.toFloat()

            val adWidth = (adWidthPixels / density).toInt()
            return AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(this, adWidth)
        }

    private var backPressedTime = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (supportActionBar != null) {
            supportActionBar?.hide()
        }
        this.setUpTabs()
        var adView=AdView(this)
        createNotificationChannel()
        alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmIntent = Intent(this, AlertReceiver::class.java).let { intent ->
            PendingIntent.getBroadcast(this, 0, intent, 0)
        }
        val calendar = Calendar.getInstance().apply {
            timeInMillis = System.currentTimeMillis()
            if (get(Calendar.HOUR_OF_DAY) >= 21) {
                add(Calendar.DAY_OF_MONTH, 1)
            }
            set(Calendar.HOUR_OF_DAY, 20)
            set(Calendar.MINUTE, 59)
        }
        alarmManager.setRepeating(
            AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
            AlarmManager.INTERVAL_DAY,
            alarmIntent
        )

        //loadBannerAd()
    }

    private fun createNotificationChannel() {
        val name = "Notification Title"
        val descriptionText = "Notification Description"
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
            description = descriptionText
        }
        val notificationManager: NotificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }

    private fun setUpTabs(){
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(MainFragment(),"")//Home
        adapter.addFragment(CravingFragment(),"")//Cravings
        adapter.addFragment(SpendingFragment(),"")//Cigarettes Spending
        adapter.addFragment(ResultFragment(),"")//Results
        adapter.addFragment(SettingsFragment(),"")//About
        viewpager.adapter = adapter
        tabLayout.setupWithViewPager(viewpager)
        tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#FF0000"))
        tabLayout.setSelectedTabIndicatorHeight(8)
        tabLayout.getTabAt(0)!!.setIcon(R.drawable.ic_baseline_home_24)
        tabLayout.getTabAt(1)!!.setIcon(R.drawable.ic_baseline_cancel_24)
        tabLayout.getTabAt(2)!!.setIcon(R.drawable.ic_baseline_attach_money_24)
        tabLayout.getTabAt(3)!!.setIcon(R.drawable.ic_baseline_check_circle_24)
        //tabLayout.getTabAt(4)!!.setIcon(R.drawable.ic_baseline_cancel_24)
        tabLayout.getTabAt(4)!!.setIcon(R.drawable.ic_baseline_table_rows_24)
    }

    override fun onBackPressed() {
        if (backPressedTime) {
            super.onBackPressed()
            return
        }
        this.backPressedTime = true
        Toast.makeText(this, "Press BACK again to exit.", Toast.LENGTH_SHORT).show()
        Handler(Looper.getMainLooper()).postDelayed({ backPressedTime = false }, 2000)
    }

    private fun loadBannerAd(){
        MobileAds.initialize(this) {}
        adView.adSize = adSize
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)
        /*mAdView.adListener = object: AdListener() {
            override fun onAdLoaded() {
                // Code to be executed when an ad finishes loading.
                Log.e("onAdLoaded - MainActivity", "Ad Loaded")
            }

            override fun onAdFailedToLoad(adError: LoadAdError) {
                // Code to be executed when an ad request fails.
                Log.e("onAdFailedToLoad - MainActivity", "Ad Failed To Load")
            }

            override fun onAdOpened() {
                // Code to be executed when an ad opens an overlay that
                // covers the screen.
                Log.e("onAdOpened - MainActivity", "Ad Opened")
            }

            override fun onAdClicked() {
                // Code to be executed when the user clicks on an ad.
                Log.e("onAdClicked - MainActivity", "Ad Clicked")
            }

            override fun onAdClosed() {
                // Code to be executed when the user is about to return
                // to the app after tapping on an ad.
                Log.e("onAdClosed - MainActivity", "Ad Closed")
            }
        }*/
    }
}