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

   val signup =findViewById<TextView>(R.id.signup)
   signup.setOnClickListener{
       Intent(this,pageSignIn::class.java).also{
           startActivity(it) }


   }
}
}