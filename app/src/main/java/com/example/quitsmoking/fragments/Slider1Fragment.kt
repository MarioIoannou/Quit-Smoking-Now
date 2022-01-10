package com.example.quitsmoking.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.example.quitsmoking.R
import kotlinx.android.synthetic.main.fragment_slider1.view.*

class Slider1Fragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_slider1, container, false)

        val vp = activity?.findViewById<ViewPager2>(R.id.vpIntroAct)

        view.next1.setOnClickListener {
            vp?.currentItem = 1
        }
        return view
    }
}