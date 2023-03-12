package com.example.penpal

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.ImageView


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val arrow = findViewById<ImageView>(R.id.mainpagearrow)

        arrow.setOnClickListener{
            Intent(this,HomePenPal::class.java).also{
                startActivity(it) }
        }
    }
        }