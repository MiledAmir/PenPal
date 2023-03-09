package com.example.penpal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView

class HomePenPal : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_pen_pal)
    }
        val arrow = findViewById<ImageView>(R.id.arrow)
        @JvmOverloads
        fun navigateToLogin(view: View? =null) {
                val intent =Intent(this, Pagelogin::class.java)
            startActivity(intent)
            }
}
