package com.example.penpal

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class pageSignIn : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_page_sign_in)

    var Signin =findViewById<TextView>(R.id.signin)

        Signin.setOnClickListener{
            Intent(this,Pagelogin::class.java).also{
                startActivity(it)}
        }
    }
}