package com.helpapp.quitsmoking

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.widget.TextView

class AboutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.helpapp.quitsmoking.R.layout.activity_about)

        val Link1: TextView? = findViewById(com.helpapp.quitsmoking.R.id.Paper_1)
        val Link2: TextView? = findViewById(com.helpapp.quitsmoking.R.id.Paper_2)
        val Link3: TextView? = findViewById(com.helpapp.quitsmoking.R.id.Paper_3)
        val Link4: TextView? = findViewById(com.helpapp.quitsmoking.R.id.Paper_4)
        val Link5: TextView? = findViewById(com.helpapp.quitsmoking.R.id.Paper_5)
        val Link6: TextView? = findViewById(com.helpapp.quitsmoking.R.id.Paper_6)
        if (Link1 != null) {
            Link1.movementMethod = LinkMovementMethod.getInstance()
        }
        if (Link2 != null) {
            Link2.movementMethod = LinkMovementMethod.getInstance()
        }
        if (Link3 != null) {
            Link3.movementMethod = LinkMovementMethod.getInstance()
        }
        if (Link4 != null) {
            Link4.movementMethod = LinkMovementMethod.getInstance()
        }
        if (Link5 != null) {
            Link5.movementMethod = LinkMovementMethod.getInstance()
        }
        if (Link6 != null) {
            Link6.movementMethod = LinkMovementMethod.getInstance()
        }
    }
}