package com.example.quitsmoking.fragments

import android.content.Intent
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
import com.example.quitsmoking.R
import com.google.android.gms.ads.*

class SpendingFragment : Fragment() {

    lateinit var mAdView : AdView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view: View = inflater.inflate(R.layout.fragment_spending, container, false)
        val PerDay: EditText = view.findViewById(R.id.HowManyPerDay)
        val PerPack: EditText = view.findViewById(R.id.HowMuchPerPack)
        val btn_spend: Button = view.findViewById(R.id.btn_spending_submit)
        //adViewSpending.addView(adView)
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
    /*private fun loadBannerAd(){
        MobileAds.initialize(requireContext()) {}
        mAdView = requireView().findViewById(R.id.adViewSpending)
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)
        mAdView.adListener = object: AdListener() {
            override fun onAdLoaded() {
                // Code to be executed when an ad finishes loading.
                Log.e("onAdLoaded - Spending", "Ad Loaded")
            }

            override fun onAdFailedToLoad(adError: LoadAdError) {
                // Code to be executed when an ad request fails.
                Log.e("onAdFailedToLoad - Spending", "Ad Failed To Load")
            }

            override fun onAdOpened() {
                // Code to be executed when an ad opens an overlay that
                // covers the screen.
                Log.e("onAdOpened - Spending", "Ad Opened")
            }

            override fun onAdClicked() {
                // Code to be executed when the user clicks on an ad.
                Log.e("onAdClicked - Spending", "Ad Clicked")
            }

            override fun onAdClosed() {
                // Code to be executed when the user is about to return
                // to the app after tapping on an ad.
                Log.e("onAdClosed - Spending", "Ad Closed")
            }
        }
    }*/
}