package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.app.Activity
import android.os.Bundle
import android.widget.Button
import android.content.Intent

class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val start_button=findViewById<Button>(R.id.start_btn)
        val chk_activity=Intent(this,CheckActivity::class.java)
        start_button.setOnClickListener{
            startActivity(chk_activity)
        }
    }
}