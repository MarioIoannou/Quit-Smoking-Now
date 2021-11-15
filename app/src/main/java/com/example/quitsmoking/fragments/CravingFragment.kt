package com.example.quitsmoking.fragments

import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.example.quitsmoking.R

class CravingFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater!!.inflate(R.layout.fragment_craving, container, false)
        val btn_play : Button = view.findViewById(R.id.play_craving)
        //val btn_reset : Button = view.findViewById(R.id.reset_craving)
        val carvingText : TextView = view.findViewById(R.id.CravingText)
        btn_play.setOnClickListener {
            object : CountDownTimer(240100, 1000) {

                override fun onTick(millisUntilFinished: Long) {
                    carvingText.setText("" + millisUntilFinished / 1000 + "")
                    carvingText.textSize = 130F
                    btn_play.visibility = View.INVISIBLE
                }

                override fun onFinish() {
                    carvingText.text = "DONE!"
                    carvingText.textSize = 100F
                    carvingText.setTextColor(Color.RED)
                }
            }.start()
        }
        return view
    }
}