package com.example.penpal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class PageSignUp : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_page_sign_up)

        // Initialize Firebase Auth
        auth = Firebase.auth


        val signin = findViewById<TextView>(R.id.signin)

        signin.setOnClickListener {
            Intent(this, Pagelogin::class.java).also {
                startActivity(it)
            }
        }

        val signUpBtn = findViewById<Button>(R.id.signup_btn)

        signUpBtn.setOnClickListener{
            performSignUp()
        }

    }

    private fun performSignUp() {
        val email = findViewById<EditText>(R.id.newemail)
        val password = findViewById<EditText>(R.id.newpassword)
        val fullname = findViewById<EditText>(R.id.fullname)



        if (email.text.isEmpty() || password.text.isEmpty()){
            Toast.makeText(this,"Please fill all fields",Toast.LENGTH_SHORT).show()
            return
        }

        val inputEmail = email.text.toString()
        val inputPassword = password.text.toString()
        val inputfullname = fullname.text.toString()
        auth.createUserWithEmailAndPassword(inputEmail, inputPassword)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    val intent = Intent(this, Homemenu::class.java)
                    intent.putExtra("myname",inputfullname)
                    startActivity(intent)
                    Toast.makeText(baseContext, "Success", Toast.LENGTH_SHORT).show()
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(baseContext, "Authentication failed.", Toast.LENGTH_SHORT).show()
                }
            }
            .addOnFailureListener{
                Toast.makeText(this, "Error occured ${it.localizedMessage}",Toast.LENGTH_SHORT).show()
            }
    }
}