package com.example.quitsmoking.fragments

import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import com.example.quitsmoking.R
import com.google.android.gms.ads.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_settings.*

class SettingsFragment : Fragment() {

    lateinit var mAdView : AdView
    // var link1: hy? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    /*private fun setupActionBar(){
        setSupportActionBar(findViewById(R.id.toolbar_settings))
        val actionbar = supportActionBar
        if(actionbar != null){
            actionbar.setDisplayHomeAsUpEnabled(true)
            actionbar.setDisplayShowTitleEnabled(false)
            actionbar.setHomeAsUpIndicator(R.drawable.back_arrow)
        }
    }*/

    /*override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }*/
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater!!.inflate(R.layout.fragment_settings, container, false)
        //adViewAbout.addView(adView)
        val Link1: TextView? = view.findViewById(R.id.link1)
        val Link2: TextView? = view.findViewById(R.id.link2)
        val Link3: TextView? = view.findViewById(R.id.link3)
        if (Link1 != null) {
            Link1.movementMethod = LinkMovementMethod.getInstance()
        }
        if (Link2 != null) {
            Link2.movementMethod = LinkMovementMethod.getInstance()
        }
        if (Link3 != null) {
            Link3.movementMethod = LinkMovementMethod.getInstance()
        }
        return view
    }

    /*private fun loadBannerAd(){
        MobileAds.initialize(requireContext()) {}
        mAdView = requireView().findViewById(R.id.adViewAbout)
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)
        mAdView.adListener = object: AdListener() {
            override fun onAdLoaded() {
                // Code to be executed when an ad finishes loading.
                Log.e("onAdLoaded - About", "Ad Loaded")
            }

            override fun onAdFailedToLoad(adError: LoadAdError) {
                // Code to be executed when an ad request fails.
                Log.e("onAdFailedToLoad - About", "Ad Failed To Load")
            }

            override fun onAdOpened() {
                // Code to be executed when an ad opens an overlay that
                // covers the screen.
                Log.e("onAdOpened - About", "Ad Opened")
            }

            override fun onAdClicked() {
                // Code to be executed when the user clicks on an ad.
                Log.e("onAdClicked - About", "Ad Clicked")
            }

            override fun onAdClosed() {
                // Code to be executed when the user is about to return
                // to the app after tapping on an ad.
                Log.e("onAdClosed - About", "Ad Closed")
            }
        }
    }*/

}