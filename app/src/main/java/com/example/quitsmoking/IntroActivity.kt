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
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import kotlinx.android.synthetic.main.activity_intro.*

class IntroActivity : AppCompatActivity() {
    private var myViewPagerAdapter: MyViewPagerAdapter? = null
    private var dotsLayout: LinearLayout? = null
    private var layouts: IntArray? = null
    private var prefManager: PrefManager? = null

    @RequiresApi(Build.VERSION_CODES.R)
    @SuppressLint("ObsoleteSdkInt")
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(android.R.style.Theme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)
        prefManager = PrefManager(this)
        if (!prefManager!!.isFirstTimeLaunch()) {
            launchHomeScreen()
            finish()
        }
        // Making notification bar transparent
        /*if (Build.VERSION.SDK_INT >= 21) {
            window.setDecorFitsSystemWindows(false)
        } else {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        }*/
        //Set layout
        setContentView(R.layout.activity_intro)

        dotsLayout = findViewById<LinearLayout>(R.id.layoutDots)
        // layouts of all intro sliders
        layouts = intArrayOf(R.layout.slider1, R.layout.slider2,R.layout.slider3)

        // adding bottom dots
        addBottomDots(0)

        // making notification bar transparent
        changeStatusBarColor()
        if (Build.VERSION.SDK_INT >= 21) {
            window.decorView.systemUiVisibility =
                SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        }
        myViewPagerAdapter = MyViewPagerAdapter()
        view_pager!!.adapter = myViewPagerAdapter
        view_pager!!.addOnPageChangeListener(viewPagerPageChangeListener)

        //btn_skip!!.setOnClickListener { launchHomeScreen() }

        btn_next!!.setOnClickListener{
            // checking for last page & if last page home screen will be launched
            val current = getItem(+1)
            if (current < layouts!!.size) {
                // move to next screen
                view_pager!!.currentItem = current
            } else {
                launchHomeScreen()
            }
        }
    }

    private fun addBottomDots(currentPage: Int) {
        val dots: Array<TextView?> = arrayOfNulls(layouts!!.size)

        dotsLayout!!.removeAllViews()
        for (i in dots.indices) {
            dots[i] = TextView(this)
            dots[i]!!.text = Html.fromHtml("&#8226;")
            dots[i]!!.textSize = 35f
            dots[i]!!.setTextColor(resources.getIntArray(R.array.array_dot_inactive)[currentPage])
            dotsLayout!!.addView(dots[i])
        }

        if (dots.isNotEmpty())
            dots[currentPage]!!.setTextColor(resources.getColor(android.R.color.white))
    }

    private fun getItem(i: Int): Int {
        return view_pager!!.currentItem + i
    }

    private fun launchHomeScreen() {
        prefManager!!.setLaunched(false)
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    //	viewpager change listener
    private var viewPagerPageChangeListener: ViewPager.OnPageChangeListener = object : ViewPager.OnPageChangeListener {

        override fun onPageSelected(position: Int) {
            addBottomDots(position)
            // changing the next button text 'NEXT' / 'GOT IT'
            if (position == layouts!!.size - 1) {
                // last page. make button text to GOT IT
                btn_next!!.text = getString(R.string.start)
                //btn_skip!!.visibility = View.GONE
            } else {
                // still pages are left
                btn_next!!.text = getString(R.string.next)
                //btn_skip!!.visibility = View.VISIBLE
            }
        }

        override fun onPageScrolled(arg0: Int, arg1: Float, arg2: Int) {
        }

        override fun onPageScrollStateChanged(arg0: Int) {
        }
    }

    @SuppressLint("ObsoleteSdkInt")
    private fun changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window = window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = Color.TRANSPARENT
        }
    }

    /**
     * View pager adapter
     */
    inner class MyViewPagerAdapter : PagerAdapter() {
        private var layoutInflater: LayoutInflater? = null

        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            layoutInflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val view = layoutInflater!!.inflate(layouts!![position], container, false)
            container.addView(view)
            return view
        }

        override fun getCount(): Int {
            return layouts!!.size
        }

        override fun isViewFromObject(view: View, obj: Any): Boolean {
            return view === obj
        }

        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
            val view = `object` as View
            container.removeView(view)
        }
    }
}