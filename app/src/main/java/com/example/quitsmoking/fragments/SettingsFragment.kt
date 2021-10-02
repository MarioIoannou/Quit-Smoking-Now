package com.example.quitsmoking.fragments

import android.os.Bundle
import android.text.method.LinkMovementMethod
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import com.example.quitsmoking.R
import kotlinx.android.synthetic.main.fragment_settings.*

class SettingsFragment : Fragment() {
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

}