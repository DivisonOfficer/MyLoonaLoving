package com.example.myloonaloving

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myloonaloving.databinding.ActivityTechLabBinding

class TechLab : BaseActivity() {
    val bind by binding<ActivityTechLabBinding>(R.layout.activity_tech_lab)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContentView(R.layout.activity_tech_lab)

        var lylicFinder = AlsongLylicFinder("Loona","Whynot").apply{

        }

    }
}