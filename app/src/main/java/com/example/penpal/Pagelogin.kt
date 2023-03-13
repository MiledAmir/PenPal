package com.example.penpal

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
<<<<<<< HEAD
=======
import android.widget.EditText
>>>>>>> d25410e758b186baeccfccfbdcd3716f277a64e8
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions


class Pagelogin : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pagelogin)

        val signup = findViewById<TextView>(R.id.signup)
        val login = findViewById<Button>(R.id.login_btn)
        val email = findViewById<EditText>(R.id.email)
        val pwd = findViewById<EditText>(R.id.password)
        val error = findViewById<TextView>(R.id.error)
        val correctemail = "abc@gmail.com"
        val correctpwd = "azerty"

        login.setOnClickListener {
            val txtemail = email.text.toString()
            val txtpwd = pwd.text.toString()
            if (txtemail.trim().isEmpty() || txtpwd.trim().isEmpty()){
             error.text="Please fill in the blank spaces"
             error.visibility = View.VISIBLE }
               else{ if (correctemail == txtemail && correctpwd == txtpwd) {
                    Intent(this, Homemenu::class.java).also {
                        startActivity(it) } }
            else{ error.text="The username or password provided in the request are invalid"
                error.visibility =View.VISIBLE}}
        }

        // Configure sign-in to request the user's ID, email address, and basic
// profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        // Configure sign-in to request the user's ID, email address, and basic
// profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()

        // Build a GoogleSignInClient with the options specified by gso.
        val mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        val account = GoogleSignIn.getLastSignedInAccount(this)

        val google_connect = findViewById<Button>(R.id.google_connect)
        google_connect.visibility = View.VISIBLE
    }
}
        signup.setOnClickListener{
            Intent(this, PageSignIn::class.java).also {
                startActivity(it) } }
    } }

