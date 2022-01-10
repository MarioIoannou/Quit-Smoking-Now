package com.example.quitsmoking

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.View.SYSTEM_UI_FLAG_LAYOUT_STABLE
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.LinearLayout
import android.widget.TableLayout
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.view.WindowCompat
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.quitsmoking.fragments.Slider1Fragment
import com.example.quitsmoking.fragments.Slider2Fragment
import com.example.quitsmoking.fragments.Slider3Fragment
import com.example.quitsmoking.fragments.adapters.IntroViewPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_intro.*
import kotlinx.android.synthetic.main.fragment_view_adapter.view.*
import kotlinx.coroutines.DelicateCoroutinesApi

class IntroActivity : AppCompatActivity() {
    private val prevstarted = "yes"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)
        Finished()
        val fragmentList = arrayListOf<Fragment>(
            Slider1Fragment(),
            Slider2Fragment(),
            Slider3Fragment()
        )

        val adapter = IntroViewPagerAdapter(
            fragmentList,
            supportFragmentManager,
            lifecycle
        )
        vpIntroAct.adapter = adapter
    }

    private fun Finished() {
        val sharedPref = getSharedPreferences("finish", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        if (sharedPref.getBoolean(prevstarted, true)) {
            editor.putBoolean(prevstarted, false)
            editor.apply()
        } else {
            Intent(this, MainActivity::class.java).also {
                startActivity(it)
                finish()
            }
        }
    }
}