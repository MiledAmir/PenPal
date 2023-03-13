package com.example.penpal

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity



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

        signup.setOnClickListener{
            Intent(this, PageSignIn::class.java).also {
                startActivity(it) } }
    }
}





