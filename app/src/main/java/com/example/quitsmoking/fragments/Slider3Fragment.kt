package com.example.quitsmoking.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.quitsmoking.MainActivity
import com.example.quitsmoking.R
import kotlinx.android.synthetic.main.fragment_slider1.view.*
import kotlinx.android.synthetic.main.fragment_slider3.*
import kotlinx.android.synthetic.main.fragment_slider3.view.*

class Slider3Fragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_slider3, container, false)

        val vp = activity?.findViewById<ViewPager2>(R.id.vpIntroAct)

        view.finish.setOnClickListener {
            activity?.let{
                val intent = Intent (it, MainActivity::class.java)
                it.startActivity(intent)
            }
        }
        return view
    }

}