package com.example.penpal

import android.graphics.drawable.GradientDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
<<<<<<< HEAD
import android.widget.Button
import androidx.core.content.ContextCompat
=======
import com.google.firebase.auth.FirebaseAuth
>>>>>>> 65c2a2b3b5a0116f03f4cbec1110b01565bf2ccd

class Homemenu : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_homemenu)

        auth = FirebaseAuth.getInstance()
    }
}