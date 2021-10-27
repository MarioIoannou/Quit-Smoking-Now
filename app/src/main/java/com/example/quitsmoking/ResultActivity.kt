package com.example.quitsmoking

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_result.*

class ResultActivity : AppCompatActivity() {
    private var backPressedTime = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        if (supportActionBar != null) {
            supportActionBar?.hide()
        }
        setupActionBar()
        var firstTime = intent?.getIntExtra("first",1)
        println("firstTime: $firstTime")
        if(firstTime == 1){
            ResultText.setText("This is your first time in the app, you should better start fresh tomorrow.")
            ResultText.setTextSize(30F)
            ResultText.setTextColor(Color.BLACK)
            firstTime++
        }else{
            Toast.makeText(
                this@ResultActivity,
                "ERROR",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    private fun setupActionBar(){
        setSupportActionBar(findViewById(R.id.toolbar_Question))
        val actionbar = supportActionBar
        if(actionbar != null){
            actionbar.setDisplayHomeAsUpEnabled(true)
            actionbar.setDisplayShowTitleEnabled(false)
            actionbar.setHomeAsUpIndicator(R.drawable.back_arrow)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}