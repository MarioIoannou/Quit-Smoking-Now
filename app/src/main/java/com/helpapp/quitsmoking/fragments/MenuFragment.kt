package com.helpapp.quitsmoking.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import com.helpapp.quitsmoking.*
import com.helpapp.quitsmoking.AboutActivity
import com.helpapp.quitsmoking.CravingActivity
import com.helpapp.quitsmoking.R
import com.helpapp.quitsmoking.SpendingActivity


class MenuFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_menu, container, false)

        val CravingsCard = view.findViewById<CardView>(R.id.CravingsTab)
        val SpendingCard = view.findViewById<CardView>(R.id.SpendingTab)
        val AboutCard = view.findViewById<CardView>(R.id.AboutTab)

        CravingsCard.setOnClickListener {
            Intent(requireContext(), CravingActivity::class.java).also {
                startActivity(it)
            }
        }

        SpendingCard.setOnClickListener {
            Intent(requireContext(), SpendingActivity::class.java).also {
                startActivity(it)
            }
        }

        AboutCard.setOnClickListener {
            Intent(requireContext(), AboutActivity::class.java).also {
                startActivity(it)
            }
        }
        return view
    }
}