package com.example.quitsmoking.fragments

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
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
    var a: Int = 0
    private val broadCastReceiver = object : BroadcastReceiver() {
        override fun onReceive(contxt: Context?, intent: Intent?) {
            Log.e("onReceive Fragment","Triggered")
            if (intent?.action == "MY_ACTION"){
                a = intent.getIntExtra("Number",0)
                println("The a has value $a")
            }
           /* when (intent?.action) {
                "MY_ACTION_NONE" -> {
                    a = 1
                }
                "MY_ACTION_LESS" -> {
                    a = 2
                }
                "MY_ACTION_EQUAL" -> {
                    a = 3
                }
                "MY_ACTION_MORE" -> {
                    a = 4
                }
                else -> Log.e("NO Receive","Nothing received")
            }*/
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val view: View = inflater.inflate(R.layout.fragment_result, container, false)
        val txtTilte: TextView = view.findViewById(R.id.ResultTextTitle)
        val txt: TextView = view.findViewById(R.id.ResultText)
        val txtbullet: TextView = view.findViewById(R.id.ResultBullet)
        if(a == 1){
            Log.e("No consumption","Triggered")
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
        if(a == 2){
            Log.e("Less","Triggered")
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
        if(a == 3){
            Log.e("Equal","Triggered")
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
        if(a == 4){
            Log.e("More","Triggered")
            txtTilte.setCompoundDrawablesWithIntrinsicBounds(R.drawable.angry_doctor_small,0,0,0)
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
        return view
    }

    override fun onResume() {
        super.onResume()
        LocalBroadcastManager.getInstance(requireActivity()).registerReceiver(broadCastReceiver,
            IntentFilter("MY_ACTION"))
    }

    override fun onPause() {
        super.onPause()
        LocalBroadcastManager.getInstance(requireActivity()).unregisterReceiver(broadCastReceiver)
    }

}