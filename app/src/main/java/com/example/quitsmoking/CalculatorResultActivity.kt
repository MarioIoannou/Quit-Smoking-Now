package com.example.quitsmoking

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_calculator_result.*

class CalculatorResultActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    private var backPressedTime = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculator_result)
        if (supportActionBar != null) {
            supportActionBar?.hide()
        }
        //intent.putExtra("Pref",smokepref.text.toString())
        setupActionBar()
        val Pref = intent?.getStringExtra("Pref")
        println("PerDay: $Pref")
        val PerDay = intent?.getStringExtra("Perday")
        println("PerDay: $PerDay")
        val PerPack = intent?.getStringExtra("Perpack")
        println("PerPack: $PerPack")
        if(Pref.toString() == "A pack of cigarettes"){
            val resultDay: Float = ((PerDay!!.toFloat() * PerPack!!.toFloat()) / 20)
            val resultWeek: Float = resultDay * 7
            val resultMonth: Float = resultDay * 30
            val resultYear: Float = resultDay * 365
            println("The resultDay is $resultDay")
            println("The resultWeek is $resultWeek")
            println("The resultMonth is $resultMonth")
            println("The resultYear is $resultYear")
            CalcDay.text = "After 1 Day you will save $resultDay €"
            CalcDay.textSize = 15F
            CalcDay.setTextColor(Color.BLACK)
            CalcWeek.text = "After 1 Week you will save $resultWeek €"
            CalcWeek.textSize = 15F
            CalcWeek.setTextColor(Color.BLACK)
            CalcMonth.text = "After 1 Month you will save $resultMonth €"
            CalcMonth.textSize = 15F
            CalcMonth.setTextColor(Color.BLACK)
            CalcYear.text = "After 1 Year you will save $resultYear €"
            CalcYear.textSize = 15F
            CalcYear.setTextColor(Color.BLACK)
        }else{
            val resultDay: Float = ((PerDay!!.toFloat() * PerPack!!.toFloat()) / 50)
            val resultWeek: Float = resultDay * 7
            val resultMonth: Float = resultDay * 30
            val resultYear: Float = resultDay * 365
            println("The resultDay is $resultDay")
            println("The resultWeek is $resultWeek")
            println("The resultMonth is $resultMonth")
            println("The resultYear is $resultYear")
            CalcDay.text = "After 1 Day you will save $resultDay €"
            CalcDay.textSize = 15F
            CalcDay.setTextColor(Color.BLACK)
            CalcWeek.text = "After 1 Week you will save $resultWeek €"
            CalcWeek.textSize = 15F
            CalcWeek.setTextColor(Color.BLACK)
            CalcMonth.text = "After 1 Month you will save $resultMonth €"
            CalcMonth.textSize = 15F
            CalcMonth.setTextColor(Color.BLACK)
            CalcYear.text = "After 1 Year you will save $resultYear €"
            CalcYear.textSize = 15F
            CalcYear.setTextColor(Color.BLACK)
        }
    }

    private fun setupActionBar(){
        setSupportActionBar(findViewById(R.id.toolbar_Calc))
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