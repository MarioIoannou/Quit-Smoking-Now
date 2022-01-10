package com.example.quitsmoking

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_spending.*

class SpendingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spending)
        val PerDay: EditText = findViewById(R.id.HowManyPerDay)
        val PerPack: EditText = findViewById(R.id.HowMuchPerPack)
        val btn_spend: Button = findViewById(R.id.btn_spending_submit)

        //adViewSpending.addView(adView)
        btn_spend.setOnClickListener {
            val pref = rg_pref.checkedRadioButtonId
            val smokepref = findViewById<RadioButton>(pref)
            if ((PerDay.text.toString().isBlank()) || (PerPack.text.toString().isBlank())) {
                Toast.makeText(
                    this.baseContext,
                    "Information are not provided.",
                    Toast.LENGTH_LONG
                ).show()
            } else if (PerDay.text.toString().toInt() > 60 && PerPack.text.toString()
                    .toFloat() > 10
            ) {
                Toast.makeText(
                    this.baseContext,
                    "Number of cigarettes an pack price, aren't valid.",
                    Toast.LENGTH_LONG
                ).show()
            } else if (PerDay.text.toString().toInt() > 60) {
                Toast.makeText(
                    this.baseContext,
                    "Cigarettes number, isn't valid.",
                    Toast.LENGTH_LONG
                ).show()
            } else if (PerDay.text.toString().toInt() > 70 || PerPack.text.toString()
                    .toFloat() < 1
            ) {
                Toast.makeText(
                    this.baseContext,
                    "Pack price, isn't a valid.",
                    Toast.LENGTH_LONG
                ).show()
            } else {
                val scaledown = AnimationUtils.loadAnimation(
                    this.baseContext,
                    R.anim.scale_down
                )
                val scaleup = AnimationUtils.loadAnimation(this.baseContext, R.anim.scale_up)
                val intent = Intent(this.baseContext, CalculatorResultActivity::class.java)
                intent.putExtra("Pref",smokepref.text.toString())
                intent.putExtra("Perday", PerDay.text.toString())
                intent.putExtra("Perpack", PerPack.text.toString())
                btn_spend.startAnimation(scaledown)
                btn_spend.startAnimation(scaleup)
                startActivity(intent)
            }
        }
    }
}