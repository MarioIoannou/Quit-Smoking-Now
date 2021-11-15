package com.example.quitsmoking.fragments

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.quitsmoking.CalculatorResultActivity
import com.example.quitsmoking.MainActivity
import com.example.quitsmoking.R
import com.example.quitsmoking.ResultActivity
import kotlinx.android.synthetic.main.fragment_spending.*

class SpendingFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view: View = inflater.inflate(R.layout.fragment_spending, container, false)
        val PerDay: EditText = view.findViewById(R.id.HowManyPerDay)
        val PerPack: EditText = view.findViewById(R.id.HowMuchPerPack)
        val btn_spend: Button = view.findViewById(R.id.btn_spending_submit)
        btn_spend.setOnClickListener {
            if ((PerDay.text.toString().isBlank()) || (PerPack.text.toString().isBlank())) {
                Toast.makeText(
                    activity?.baseContext,
                    "Information are not provided.",
                    Toast.LENGTH_LONG
                ).show()
            } else if (PerDay.text.toString().toInt() > 60 && PerPack.text.toString().toFloat() > 10) {
                Toast.makeText(
                    activity?.baseContext,
                    "Number of cigarettes an pack price, aren't valid.",
                    Toast.LENGTH_LONG
                ).show()
            } else if (PerDay.text.toString().toInt() > 60) {
                Toast.makeText(
                    activity?.baseContext,
                    "Cigarettes number, isn't valid.",
                    Toast.LENGTH_LONG
                ).show()
            } else if (PerDay.text.toString().toInt() > 70 || PerPack.text.toString().toFloat() <1) {
                Toast.makeText(
                    activity?.baseContext,
                    "Pack price, isn't a valid.",
                    Toast.LENGTH_LONG
                ).show()
            } else {
                val scaledown = AnimationUtils.loadAnimation(activity?.baseContext,
                    R.anim.scale_down
                )
                val scaleup = AnimationUtils.loadAnimation(activity?.baseContext, R.anim.scale_up)
                val intent = Intent(activity?.baseContext, CalculatorResultActivity::class.java)
                intent.putExtra("Perday",PerDay.text.toString())
                intent.putExtra("Perpack",PerPack.text.toString())
                btn_spend.startAnimation(scaledown)
                btn_spend.startAnimation(scaleup)
                startActivity(intent)
            }
        }
        return view
    }
}