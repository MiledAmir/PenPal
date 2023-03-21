package com.example.penpal

<<<<<<< HEAD
import android.graphics.drawable.GradientDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.content.ContextCompat
=======
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
>>>>>>> 81a2ec7e76cc875bfe17926208d5f0004a6760fb
import com.google.firebase.auth.FirebaseAuth

class Homemenu : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_homemenu)

        auth = FirebaseAuth.getInstance()
        val email = intent.getStringExtra("email")
        val displayName = intent.getStringExtra("name")

        findViewById<TextView>(R.id.userid).text = email + "\n" + displayName
        findViewById<Button>(R.id.signout).setOnClickListener {
            auth.signOut()
            startActivity(Intent(this, Pagelogin::class.java))
        }
    }
}