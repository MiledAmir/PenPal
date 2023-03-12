package com.example.penpal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class HomePenPal : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_pen_pal)

        val arrow = findViewById<ImageView>(R.id.arrow)

      arrow.setOnClickListener{
          Intent(this,Pagelogin::class.java).also{
              startActivity(it) }
      }
    }
}
