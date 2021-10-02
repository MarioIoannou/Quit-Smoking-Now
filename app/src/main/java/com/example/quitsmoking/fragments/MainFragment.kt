package com.example.quitsmoking.fragments

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.media.MediaPlayer
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import com.example.quitsmoking.R
import kotlinx.android.synthetic.main.fragment_main.*
import android.widget.TextView
import androidx.annotation.RequiresApi
import com.example.quitsmoking.MainActivity
import com.example.quitsmoking.ResultActivity
import org.joda.time.LocalDate
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.lang.Exception
import java.lang.StringBuilder
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

class MainFragment : Fragment(), View.OnClickListener {

    @RequiresApi(Build.VERSION_CODES.O)
    val time: LocalTime = LocalTime.now()
    val deadline = "21:00:00.000"
    private var timesSmoked: Int = 1
    var line: String? = null
    lateinit var sharedPreferences: SharedPreferences
    val myPreferences = "mypref"
    val timekey = "timessmoked"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //supportActionBar?.setDisplayHomeAsUpEnabled(true)
       /*val scaledown1: Animation = AnimationUtils.loadAnimation(activity?.baseContext, R.anim.scale_down)
        val scaleup1 = AnimationUtils.loadAnimation(activity?.baseContext, R.anim.scale_up)
        settings_btn.setOnClickListener{
            val mp1 = MediaPlayer.create(this, R.raw.button_click)
            mp1?.start()
            val intent = Intent(this, SettingsActivity::class.java)
            intent.putExtra("varone",intent.getStringExtra("varOne"))
            intent.putExtra("vartwo",intent.getStringExtra("varTwo"))
            settings_btn.startAnimation(scaledown1)
            settings_btn.startAnimation(scaleup1)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left)
        }*/
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var days = 1
        val view: View = inflater!!.inflate(R.layout.fragment_main, container, false)
        val cig_btn: ImageButton = view.findViewById(R.id.CigaretteButton)
        val sub_btn: Button = view.findViewById(R.id.subtractCig)
        val txt : TextView = view.findViewById(R.id.DidYouSmokeText)
        timesSmoked = loadData()
        //if(time != deadline){
        if(1==1){
            if(days == 1){
                //transaction(days)

                /*val intent1 = Intent(activity, MainActivity::class.java)
                intent1.putExtra("first",1)
                startActivity(intent1)
                println("1st print: $days")*/

                /*val transaction = activity?.supportFragmentManager?.beginTransaction()
                transaction?.replace(R.id.MainFrag, ResultFragment())
                transaction?.disallowAddToBackStack()
                transaction?.commit()*/
                days += 1
            }
            cig_btn.setOnClickListener {
                txt.text = timesSmoked.toString()
                txt.setTextSize(100F)
                txt.setTextColor(Color.RED)
                val scaledown2: Animation = AnimationUtils.loadAnimation(activity?.baseContext, R.anim.scale_down)
                val scaleup2 = AnimationUtils.loadAnimation(activity?.baseContext, R.anim.scale_up)
                when {
                    timesSmoked == 0 -> {
                        timesSmoked++
                    }
                    timesSmoked == 1 -> {
                        CigaretteButton?.startAnimation(scaledown2)
                        CigaretteButton?.startAnimation(scaleup2)
                        timesSmoked++
                        val mp2 = MediaPlayer.create(activity?.baseContext, R.raw.human_smoking)
                        mp2?.start()
                    }
                    timesSmoked >= 2 -> {
                        CigaretteButton?.startAnimation(scaledown2)
                        CigaretteButton?.startAnimation(scaleup2)
                        timesSmoked++
                        val mp3 = MediaPlayer.create(activity?.baseContext, R.raw.human_smoking)
                        mp3?.start()
                    }
                    else -> {
                        Toast.makeText(activity?.baseContext, "ERROR 404", Toast.LENGTH_SHORT).show()
                        timesSmoked++
                    }
                }
                println(timesSmoked)
                saveData("Tsigaro",timesSmoked)
            }

        }else {
            if(days > 1){
                val intent2 = Intent(requireContext(), ResultActivity::class.java)
                intent2.putExtra("smokes",timesSmoked)
                startActivity(intent2)
                days++
                println("2nd print: $days")
                Toast.makeText(activity?.baseContext, "Else...", Toast.LENGTH_SHORT).show()
            }
            timesSmoked = 1
        }
        println("3rd print: $days")

        sub_btn.setOnClickListener {
            //readData()
            if (timesSmoked > 0) {
                timesSmoked--
                txt.text = timesSmoked.toString()
                txt.setTextSize(100F)
            } else {
                Toast.makeText(
                    activity?.baseContext,
                    "You can't subtract when you didn't smoke any cigarette.",
                    Toast.LENGTH_SHORT
                ).show()
            }
            saveData("Tsigaro",timesSmoked)
        }
        return view
    }

    fun transaction(a : Int){
        if (a == 1){
            val intent1 = Intent(activity, MainActivity::class.java)
            intent1.putExtra("first",1)
            startActivity(intent1)
            println("1st print: $a")
        }else {
            return
        }
    }

    /*override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        for (fragment in MainActivity.fragments)
        { fragment.onActivityResult(requestCode, resultCode, data) }
    }*/

    private fun saveData(key: String,value: Int){
        val sharedPreferences: SharedPreferences = requireActivity().getSharedPreferences ("sharedPrefs",Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.apply {
            putInt(key, value)
        }.apply()
    }

    private fun loadData(): Int {
        val sharedPreferences: SharedPreferences = requireActivity().getSharedPreferences ("sharedPrefs",Context.MODE_PRIVATE)
        val savedString = sharedPreferences.getInt("Tsigaro",1)
        return savedString
    }

//2os Tropos

   /* fun saveData(){
        try{
            val fileOutputStream = context?.openFileOutput("file.txt",Context.MODE_PRIVATE)
            val outputStreamWriter = OutputStreamWriter(fileOutputStream)
            outputStreamWriter.write(DidYouSmokeText.text.toString())
            outputStreamWriter.close()
        }
        catch (exp: Exception){
            exp.printStackTrace()
        }
    }

    fun clearData(view: View){
        DidYouSmokeText.text = ""
    }

    fun readData(): String {
        try{
            val fileInputStream = context?.openFileInput("file.txt")
            val inputStreamReader = InputStreamReader(fileInputStream)
            val bufferReader = BufferedReader(inputStreamReader)
            val stringBuilder = StringBuilder()
            while (run {
                    line = bufferReader.readLine()
                    line
                } !=null){
                stringBuilder.append(line)
            }
            fileInputStream?.close()
            inputStreamReader.close()
            DidYouSmokeText.text = stringBuilder.toString()
        }
        catch (exp: Exception){
            exp.printStackTrace()
        }
        return DidYouSmokeText.text.toString()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("timesSmoked",timesSmoked)
    }*/

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.CigaretteButton -> {

            }

            else -> {
            }
        }
    }

}
