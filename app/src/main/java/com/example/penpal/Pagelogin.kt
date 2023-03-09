package com.example.penpal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class Pagelogin : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pagelogin)

        val google_connect = findViewById<Button>(R.id.google_connect)
            google_connect.setOnClickListener {
                Toast.makeText(this,"BUTTONS", "User tapped the Supabutton",Toast.LENGTH_LONG).show()
            }
    }
}