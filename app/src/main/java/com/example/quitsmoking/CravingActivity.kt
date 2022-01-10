package com.example.quitsmoking

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Button
import android.widget.TextView

class CravingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_craving)
        val btn_play : Button = findViewById(R.id.play_craving)
        //loadBannerAd()
        //adViewCraving.addView(adView)
        //val btn_reset : Button = view.findViewById(R.id.reset_craving)
        val carvingText : TextView = findViewById(R.id.CravingText)
        btn_play.setOnClickListener {
            object : CountDownTimer(240100, 1000) {

                @SuppressLint("SetTextI18n")
                override fun onTick(millisUntilFinished: Long) {
                    carvingText.setText("" + millisUntilFinished / 1000 + "")
                    carvingText.textSize = 130F
                    btn_play.visibility = View.INVISIBLE
                }

                @SuppressLint("SetTextI18n")
                override fun onFinish() {
                    carvingText.text = "DONE!"
                    carvingText.textSize = 100F
                    carvingText.setTextColor(Color.RED)
                }
            }.start()
        }
    }
}