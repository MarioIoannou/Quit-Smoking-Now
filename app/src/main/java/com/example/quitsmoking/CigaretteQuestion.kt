package com.example.quitsmoking

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.AnimationUtils
import android.widget.*
import androidx.preference.PreferenceManager
import kotlinx.android.synthetic.main.activity_cigarette_question.*

class CigaretteQuestion : AppCompatActivity() {
    private var backPressedTime = false
    val prevStarted: String = "yes"
    override fun onResume() {
        super.onResume()
        val sharedpreferences =
            getSharedPreferences(getString(R.string.app_name), Context.MODE_PRIVATE)
        if (!sharedpreferences.getBoolean(prevStarted, false)) {
            val editor = sharedpreferences.edit()
            editor.putBoolean(prevStarted, java.lang.Boolean.TRUE)
            editor.apply()
        } else {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cigarette_question)
        if (supportActionBar != null) {
            supportActionBar?.hide()
        }
        //setupActionBar()
        btn_submit.setOnClickListener {
            if ((HowMuchEdit.text.toString().isBlank()) || (HowOldEdit.text.toString().isBlank())) {
                Toast.makeText(
                    this@CigaretteQuestion,
                    "Cigarette consumption number or age is not provided.",
                    Toast.LENGTH_LONG
                ).show()
            } else if (HowMuchEdit.text.toString().toInt() > 60 && HowOldEdit.text.toString().toInt() > 70) {
                Toast.makeText(
                    this@CigaretteQuestion,
                    "Age and Cigarette consumption numbers aren't valid.",
                    Toast.LENGTH_LONG
                ).show()
            } else if (HowMuchEdit.text.toString().toInt() > 60) {
                Toast.makeText(
                    this@CigaretteQuestion,
                    "Cigarette consumption number isn't valid.",
                    Toast.LENGTH_LONG
                ).show()
            } else if (HowOldEdit.text.toString().toInt() > 70 || HowOldEdit.text.toString().toInt() <16) {
                Toast.makeText(
                    this@CigaretteQuestion,
                    "Age isn't a valid.",
                    Toast.LENGTH_LONG
                ).show()
            } else {
                val scaledown = AnimationUtils.loadAnimation(this, R.anim.scale_down)
                val scaleup = AnimationUtils.loadAnimation(this, R.anim.scale_up)
                val mp = MediaPlayer.create(this, R.raw.button_click)
                mp?.start()
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("varOne",HowMuchEdit.text.toString())
                intent.putExtra("varTwo",HowOldEdit.text.toString())
                btn_submit.startAnimation(scaledown)
                btn_submit.startAnimation(scaleup)
                startActivity(intent)
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                finish()
            }
        }
    }

    /*private fun setupActionBar(){
        setSupportActionBar(findViewById(R.id.toolbar_Question))
        val actionbar = supportActionBar
        if(actionbar != null){
            actionbar.setDisplayHomeAsUpEnabled(true)
            actionbar.setDisplayShowTitleEnabled(false)
            actionbar.setHomeAsUpIndicator(R.drawable.back_arrow)
        }
    }*/

    /*override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }*/
    override fun onBackPressed() {
        if (backPressedTime) {
            super.onBackPressed()
            return
        }
        this.backPressedTime = true
        Toast.makeText(this, "Press BACK again to exit.", Toast.LENGTH_SHORT).show()
        Handler(Looper.getMainLooper()).postDelayed(Runnable { backPressedTime = false }, 2000)
    }
}