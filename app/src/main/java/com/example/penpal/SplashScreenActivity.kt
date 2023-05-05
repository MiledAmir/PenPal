package com.example.penpal

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SplashScreenActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
       val ivNote = findViewById<ImageView>(R.id.iv_note)
       val desc= findViewById<TextView>(R.id.desc)






        ivNote.animate().setDuration(1500).alpha(1f).withEndAction{
          val auth = Firebase.auth
            val currentUser =auth.currentUser
            if(currentUser != null){val i = Intent(this,Homemenu::class.java)
                startActivity(i) }else{
                    Intent(this, Pagelogin::class.java).also{
                        startActivity(it)
                    } }
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
            finish()

        }
    }
}