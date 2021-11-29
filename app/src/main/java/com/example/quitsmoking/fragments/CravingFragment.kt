package com.example.quitsmoking.fragments

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.example.quitsmoking.R
import com.google.android.gms.ads.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_craving.*

class CravingFragment : Fragment() {

    lateinit var mAdView : AdView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.fragment_craving, container, false)
        val btn_play : Button = view.findViewById(R.id.play_craving)
        //loadBannerAd()
        //adViewCraving.addView(adView)
        //val btn_reset : Button = view.findViewById(R.id.reset_craving)
        val carvingText : TextView = view.findViewById(R.id.CravingText)
        btn_play.setOnClickListener {
            object : CountDownTimer(240100, 1000) {

                @SuppressLint("SetTextI18n")
                override fun onTick(millisUntilFinished: Long) {
                    carvingText.setText("" + millisUntilFinished / 1000 + "")
                    carvingText.textSize = 130F
                    btn_play.visibility = View.INVISIBLE
                }

                @SuppressLint("SetTextI18n")
                override fun onFinish() {
                    carvingText.text = "DONE!"
                    carvingText.textSize = 100F
                    carvingText.setTextColor(Color.RED)
                }
            }.start()
        }
        return view
    }
    /*private fun loadBannerAd(){
        MobileAds.initialize(requireContext()) {}
        mAdView = requireView().findViewById(R.id.adViewCraving)
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)
        mAdView.adListener = object: AdListener() {
            override fun onAdLoaded() {
                // Code to be executed when an ad finishes loading.
                Log.e("onAdLoaded - Craving", "Ad Loaded")
            }

            override fun onAdFailedToLoad(adError: LoadAdError) {
                // Code to be executed when an ad request fails.
                Log.e("onAdFailedToLoad - Craving", "Ad Failed To Load")
            }

            override fun onAdOpened() {
                // Code to be executed when an ad opens an overlay that
                // covers the screen.
                Log.e("onAdOpened - Craving", "Ad Opened")
            }

            override fun onAdClicked() {
                // Code to be executed when the user clicks on an ad.
                Log.e("onAdClicked - Craving", "Ad Clicked")
            }

            override fun onAdClosed() {
                // Code to be executed when the user is about to return
                // to the app after tapping on an ad.
                Log.e("onAdClosed - Craving", "Ad Closed")
            }
        }
    }*/
}