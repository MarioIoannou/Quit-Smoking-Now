package com.helpapp.quitsmoking.fragments

import android.content.*
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.TEXT_ALIGNMENT_CENTER
import android.view.View.TEXT_ALIGNMENT_VIEW_START
import android.view.ViewGroup
import android.widget.TextView
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.helpapp.quitsmoking.R

class ResultFragment : Fragment() {
    lateinit var Num: String
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val view: View = inflater.inflate(R.layout.fragment_result, container, false)
        val txtTilte: TextView = view.findViewById(R.id.ResultTextTitle)
        val txt: TextView = view.findViewById(R.id.ResultText)
        val txtbullet: TextView = view.findViewById(R.id.ResultBullet)
        Num = loadData().toString()
        //Num = "EqualNone"
        when (Num) {
            "None" -> {
                Log.e("No consumption", "Triggered")
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
            "Less" -> {
                Log.e("Less", "Triggered")
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
            "Equal" -> {
                Log.e("Equal", "Triggered")
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
            "EqualNone" -> {
                Log.e("EqualNone", "Triggered")
                txtTilte.text = getString(R.string.equals_none_title)
                txtTilte.textAlignment = TEXT_ALIGNMENT_CENTER
                txt.text = getString(R.string.equals_none_text)
                txt.textSize = 15F
                txt.setTextColor(Color.BLACK)
                txt.gravity = Gravity.CENTER
                txtbullet.text = getString(R.string.equals_none_bullet)
                txtbullet.textSize = 15F
                txtbullet.setTextColor(Color.BLACK)
                txtbullet.gravity = Gravity.START
            }
            "More" -> {
                Log.e("More", "Triggered")
                txtTilte.setCompoundDrawablesWithIntrinsicBounds(R.drawable.angry_doctor_small, 0, 0, 0)
                txtTilte.text = " "
                txt.text = getString(R.string.more_day_text)
                txt.textSize = 15F
                txt.setTextColor(Color.BLACK)
                txt.gravity = Gravity.CENTER
                txtbullet.text = getString(R.string.more_day_bullet)
                txtbullet.textSize = 15F
                txtbullet.setTextColor(Color.BLACK)
                txtbullet.gravity = Gravity.START
            }
            "FirstTimer" -> {
                Log.e("More - First Time", "Triggered")
                txtTilte.text = getString(R.string.more_first_time_title)
                txt.text = getString(R.string.more_first_time_text)
                txt.textSize = 15F
                txt.setTextColor(Color.BLACK)
                txt.gravity = Gravity.CENTER
                txtbullet.text = getString(R.string.more_first_time_bullet)
                txtbullet.textSize = 15F
                txtbullet.setTextColor(Color.BLACK)
                txtbullet.gravity = Gravity.START
            }
        }
        return view
    }

    private fun loadData(): String? {
        val sharedPreferences: SharedPreferences =
            requireActivity().getSharedPreferences("sharedPrefsData", Context.MODE_PRIVATE)
        val savedString = sharedPreferences.getString("Number", null)
        return savedString
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

}