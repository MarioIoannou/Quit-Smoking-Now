package com.example.quitsmoking.fragments

import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.example.quitsmoking.R

class ResultFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val view: View = inflater.inflate(R.layout.fragment_result, container, false)
        var a : Int = 2
        a = 1
        val txtTilte: TextView = view.findViewById(R.id.ResultTextTitle)
        val txt: TextView = view.findViewById(R.id.ResultText)
        val txtbullet: TextView = view.findViewById(R.id.ResultBullet)
        if(a == 0){
            txtTilte.text = getString(R.string.no_consumption_title)
            txt.text = getString(R.string.no_consumption_text)
            txt.textSize = 15F
            txt.setTextColor(Color.BLACK)
            txt.gravity = Gravity.CENTER
            txtbullet.text = getString(R.string.no_consumption_bullet)
            txtbullet.textSize = 15F
            txtbullet.setTextColor(Color.BLACK)
            txtbullet.gravity = Gravity.START
        }
        if(a == 1){
            txtTilte.text = getString(R.string.less_day_title)
            txt.text = getString(R.string.less_day_text)
            txt.textSize = 15F
            txt.setTextColor(Color.BLACK)
            txt.gravity = Gravity.CENTER
            txtbullet.text = getString(R.string.less_day_bullet)
            txtbullet.textSize = 15F
            txtbullet.setTextColor(Color.BLACK)
            txtbullet.gravity = Gravity.START
        }
        if(a == 2){
            txtTilte.text = getString(R.string.more_equals_less_title)
            txt.text = getString(R.string.more_equal_less_text)
            txt.textSize = 15F
            txt.setTextColor(Color.BLACK)
            txt.gravity = Gravity.CENTER
            txtbullet.text = getString(R.string.more_equals_less_bullet)
            txtbullet.textSize = 15F
            txtbullet.setTextColor(Color.BLACK)
            txtbullet.gravity = Gravity.START
        }
        if(a == 3){
            txtTilte.setCompoundDrawablesWithIntrinsicBounds(R.drawable.angry_doctor,0,0,0)
            txt.text = getString(R.string.more_day_text)
            txt.textSize = 15F
            txt.setTextColor(Color.BLACK)
            txt.gravity = Gravity.CENTER
            txtbullet.text = getString(R.string.more_equals_less_bullet)
            txtbullet.textSize = 15F
            txtbullet.setTextColor(Color.BLACK)
            txtbullet.gravity = Gravity.START
        }
        return view
    }

}