package com.example.penpal

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Paint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class Pagelogin : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pagelogin)

<<<<<<< HEAD
        val google_connect = findViewById<Button>(R.id.google_connect)
            google_connect.setOnClickListener {
                Toast.makeText(this,"BUTTONS", "User tapped the Supabutton",Toast.LENGTH_LONG).show()
            }
=======



>>>>>>> 08e377ce185577c2fb96c8a78bc510bb510ff5e9

        val sign_in_text = findViewById<TextView>(R.id.sign_in_text)
        sign_in_text.paintFlags = sign_in_text.paintFlags or Paint.UNDERLINE_TEXT_FLAG
        sign_in_text.text="mon nouveau"

   val signup =findViewById<TextView>(R.id.signup)
   signup.setOnClickListener{
       Intent(this,pageSignIn::class.java).also{
           startActivity(it) }
   }
}
}