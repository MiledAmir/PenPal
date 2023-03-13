package com.example.penpal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class PageSignIn : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_page_sign_in)

        val signin = findViewById<TextView>(R.id.signin)

        signin.setOnClickListener {
            Intent(this, Pagelogin::class.java).also {
                startActivity(it)
            }
        }
    }
}