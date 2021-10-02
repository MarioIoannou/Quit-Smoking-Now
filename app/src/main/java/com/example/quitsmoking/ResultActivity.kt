package com.example.quitsmoking

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_result.*

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        if (supportActionBar != null) {
            supportActionBar?.hide()
        }
        val firstTime = intent?.getIntExtra("First",1)
        println("firstTime: $firstTime")
        if(firstTime == 1){
            ResultText.setText("This is your first time in the app, you should better start fresh tomorrow.")
            ResultText.setTextSize(50F)
            ResultText.setTextColor(Color.BLACK)
        }else{
            Toast.makeText(
                this@ResultActivity,
                "ERROR",
                Toast.LENGTH_LONG
            ).show()
        }
    }
}