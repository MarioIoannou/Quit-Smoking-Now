package com.example.quitsmoking.fragments

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.quitsmoking.R
import kotlinx.android.synthetic.main.activity_result.*


class ResultFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater!!.inflate(R.layout.fragment_main, container, false)
        var days = 1
        //val firstTime = intent?.getIntExtra("First", 1)
        //println("firstTime: $firstTime")
        println("Days_2: $days")
        if (days == 1) {
            ResultText?.text = "This is your first time in the app, you should better start fresh tomorrow."
            ResultText?.setTextSize(50F)
            ResultText?.setTextColor(Color.BLACK)
            days ++
        } else {
            Toast.makeText(
                activity?.baseContext,
                "ERROR",
                Toast.LENGTH_LONG
            ).show()
        }
        return view
    }

}