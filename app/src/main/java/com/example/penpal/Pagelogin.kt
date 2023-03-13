package com.example.penpal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class Pagelogin : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pagelogin)

        val signup = findViewById<TextView>(R.id.signup)

        signup.setOnClickListener {
            Intent(this, PageSignIn::class.java).also {
                startActivity(it)
            }
        }
    }
}

