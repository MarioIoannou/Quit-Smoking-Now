package com.example.quitsmoking

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.provider.SyncStateContract
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.quitsmoking.fragments.MainFragment
import com.example.quitsmoking.fragments.ResultFragment
import com.example.quitsmoking.fragments.SettingsFragment
import com.example.quitsmoking.fragments.adapters.ViewPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*
import java.net.URLClassLoader.newInstance




private const val TIMES_SMOKED = "Times Smoked"

class MainActivity : AppCompatActivity() {
   /*lateinit var toggle: ActionBarDrawerToggle
    var timesSmoked = 1*/

    private var backPressedTime = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (supportActionBar != null) {
            supportActionBar?.hide()
        }
        setUpTabs()

        /*var extra1 = intent.getIntExtra("first",1)
        onStart(extra1)
        println("extra1: $extra1")
        if (extra1 == 1){
            val intent1 = Intent(this, ResultActivity::class.java)
            intent1.putExtra("First",extra1)
            startActivity(intent1)
        }
        extra1 += 1*/

    //Pass data
        /*val extra1=intent.getStringExtra("varOne")
        val extra2=intent.getStringExtra("varTwo")
        val data = Bundle()
        data.putString("data1",extra1)
        data.putString("data2",extra2)
        val settngs : SettingsFragment = SettingsFragment()*/

    //Non fragment
        /*addCigarette()
        SubCigarette()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val scaledown1: Animation = AnimationUtils.loadAnimation(this, R.anim.scale_down)
        val scaleup1 = AnimationUtils.loadAnimation(this, R.anim.scale_up)
        settings_btn.setOnClickListener{
            val mp1 = MediaPlayer.create(this, R.raw.button_click)
            mp1?.start()
            val intent = Intent(this, SettingsActivity::class.java)
            intent.putExtra("varone",intent.getStringExtra("varOne"))
            intent.putExtra("vartwo",intent.getStringExtra("varTwo"))
            settings_btn.startAnimation(scaledown1)
            settings_btn.startAnimation(scaleup1)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left)
        }*/
    }

    fun onStart(a :Int) {
        super.onStart()
        if (a == 1) {
            if (a == 1){
                val intent1 = Intent(this, ResultActivity::class.java)
                intent1.putExtra("First",a)
                startActivity(intent1)
            }
        }
    }

    private fun setUpTabs(){
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(MainFragment(),"Home")
        adapter.addFragment(SettingsFragment(),"About")
        viewpager.adapter = adapter
        tabLayout.setupWithViewPager(viewpager)
        tabLayout.getTabAt(0)!!.setIcon(R.drawable.ic_baseline_home_24)
        tabLayout.getTabAt(1)!!.setIcon(R.drawable.ic_baseline_table_rows_24)
    }
    /*private fun addCigarette() {
        CigaretteButton.setOnClickListener {
            DidYouSmokeText.text = timesSmoked.toString()
            DidYouSmokeText.setTextSize(100F)
            DidYouSmokeText.setTextColor(Color.RED)
            val scaledown2: Animation = AnimationUtils.loadAnimation(this, R.anim.scale_down)
            val scaleup2 = AnimationUtils.loadAnimation(this, R.anim.scale_up)
            when {
                timesSmoked == 0 -> {
                    timesSmoked++
                }
                timesSmoked == 1 -> {
                    CigaretteButton.startAnimation(scaledown2)
                    CigaretteButton.startAnimation(scaleup2)
                    Toast.makeText(
                        this@MainActivity,
                        "You smoked $timesSmoked cigarette.",
                        Toast.LENGTH_SHORT
                    ).show()
                    timesSmoked++
                    val mp2 = MediaPlayer.create(this, R.raw.human_smoking)
                    mp2?.start()
                }
                timesSmoked >= 2 -> {
                    CigaretteButton.startAnimation(scaledown2)
                    CigaretteButton.startAnimation(scaleup2)
                    Toast.makeText(
                        this@MainActivity,
                        "You smoked $timesSmoked cigarettes.",
                        Toast.LENGTH_SHORT
                    ).show()
                    timesSmoked++
                    val mp3 = MediaPlayer.create(this, R.raw.human_smoking)
                    mp3?.start()
                }
                else -> {
                    Toast.makeText(this@MainActivity, "ERROR 404", Toast.LENGTH_SHORT).show()
                    timesSmoked++
                }
            }
        }
    }

    private fun SubCigarette() {
        subtractCig.setOnClickListener {
            if (timesSmoked > 0) {
                timesSmoked--
                DidYouSmokeText.text = timesSmoked.toString()
                DidYouSmokeText.setTextSize(100F)
            } else {
                Toast.makeText(
                    this@MainActivity,
                    "You can't subtract when you didn't smoke any cigarette.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }*/

    override fun onBackPressed() {
        if (backPressedTime) {
            super.onBackPressed()
            return
        }
        this.backPressedTime = true
        Toast.makeText(this, "Press BACK again to exit.", Toast.LENGTH_SHORT).show()
        Handler(Looper.getMainLooper()).postDelayed({ backPressedTime = false }, 2000)
    }
}