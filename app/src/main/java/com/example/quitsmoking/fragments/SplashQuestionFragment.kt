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
import android.widget.Toast
import com.example.quitsmoking.MainActivity
import com.example.quitsmoking.R
import kotlinx.android.synthetic.main.activity_cigarette_question.*

class SplashQuestionFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater!!.inflate(R.layout.fragment_main, container, false)
        val question_btn: Button = view.findViewById(R.id.btn_submit)
        question_btn.setOnClickListener {
            if ((HowMuchEdit.text.toString().isBlank()) || (HowOldEdit.text.toString().isBlank())) {
                Toast.makeText(
                    activity?.baseContext,
                    "Cigarette consumption number or age is not provided.",
                    Toast.LENGTH_LONG
                ).show()
            } else if (HowMuchEdit.text.toString().toInt() > 60 && HowOldEdit.text.toString().toInt() > 70) {
                Toast.makeText(
                    activity?.baseContext,
                    "Age and Cigarette consumption numbers aren't valid.",
                    Toast.LENGTH_LONG
                ).show()
            } else if (HowMuchEdit.text.toString().toInt() > 60) {
                Toast.makeText(
                    activity?.baseContext,
                    "Cigarette consumption number isn't valid.",
                    Toast.LENGTH_LONG
                ).show()
            } else if (HowOldEdit.text.toString().toInt() > 70 || HowOldEdit.text.toString().toInt() <16) {
                Toast.makeText(
                    activity?.baseContext,
                    "Age isn't a valid.",
                    Toast.LENGTH_LONG
                ).show()
            } else {
                val scaledown = AnimationUtils.loadAnimation(activity?.baseContext,
                    R.anim.scale_down
                )
                val scaleup = AnimationUtils.loadAnimation(activity?.baseContext, R.anim.scale_up)
                val mp = MediaPlayer.create(activity?.baseContext, R.raw.button_click)
                mp?.start()
                val intent = Intent(activity?.baseContext, MainActivity::class.java)
                intent.putExtra("varOne",HowMuchEdit.text.toString())
                intent.putExtra("varTwo",HowOldEdit.text.toString())
                btn_question_submit.startAnimation(scaledown)
                btn_question_submit.startAnimation(scaleup)
                startActivity(intent)
            }
        }
        return view
    }
}