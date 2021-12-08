package com.example.quitsmoking.fragments

import android.content.*
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.example.quitsmoking.R

class ResultFragment : Fragment() {
    var Num: Int = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val view: View = inflater.inflate(R.layout.fragment_result, container, false)
        val txtTilte: TextView = view.findViewById(R.id.ResultTextTitle)
        val txt: TextView = view.findViewById(R.id.ResultText)
        val txtbullet: TextView = view.findViewById(R.id.ResultBullet)
        Num = loadData()
        if (Num == 1) {
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
        if (Num == 2) {
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
        if (Num == 3) {
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
        if (Num == 4) {
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
        if (Num == 5) {
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
        return view
    }

    private fun loadData(): Int {
        val sharedPreferences: SharedPreferences =
            requireActivity().getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
        val savedString = sharedPreferences.getInt("Number", -1)
        return savedString
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

}