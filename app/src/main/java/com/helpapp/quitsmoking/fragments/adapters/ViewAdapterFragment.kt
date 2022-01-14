package com.helpapp.quitsmoking.fragments.adapters

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.helpapp.quitsmoking.R
import com.helpapp.quitsmoking.fragments.Slider1Fragment
import com.helpapp.quitsmoking.fragments.Slider2Fragment
import com.helpapp.quitsmoking.fragments.Slider3Fragment
import kotlinx.android.synthetic.main.fragment_view_adapter.view.*

class ViewAdapterFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view =  inflater.inflate(R.layout.fragment_view_adapter, container, false)

        val fragmentList = arrayListOf<Fragment>(
            Slider1Fragment(),
            Slider2Fragment(),
            Slider3Fragment()
        )

        val adapter = IntroViewPagerAdapter(
            fragmentList,
            requireActivity().supportFragmentManager,
            lifecycle
        )

        view.vpIntro.adapter = adapter
        return view
    }
}