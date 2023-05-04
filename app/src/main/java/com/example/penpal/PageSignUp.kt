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
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class PageSignUp : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    lateinit var fullname: EditText
    lateinit var newemail: EditText
    lateinit var newpassword: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_page_sign_up)

        fullname = findViewById(R.id.fullname)
        newemail = findViewById(R.id.newemail)
        newpassword = findViewById(R.id.newpassword)

        // Initialize Firebase Auth
        auth = Firebase.auth


        val signUpBtn = findViewById<Button>(R.id.signup_btn)

        signUpBtn.setOnClickListener {


            val name = fullname.text.toString()
            val email = newemail.text.toString()
            val password = newpassword.text.toString()

            if (email.isEmpty() || password.isEmpty() || name.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            } else {
                auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                       val user= hashMapOf(
                           "fullname" to name,
                           "email" to email )

                        val currentUser = auth.currentUser
                        val db=Firebase.firestore
                        db.collection("users").document(currentUser!!.uid).set(user).addOnSuccessListener {
                            Intent(this, Homemenu::class.java).also {
                                startActivity(it)
                                Toast.makeText(baseContext, "Success", Toast.LENGTH_SHORT).show() }
                        }.addOnFailureListener{

                        }

                         }
                    else{ Toast.makeText(baseContext, "Authentication failed.", Toast.LENGTH_SHORT).show() }
                }
                    .addOnFailureListener{
                        Toast.makeText(this, "Error occured ${it.localizedMessage}",Toast.LENGTH_SHORT).show()
                    }
            }


            val signin = findViewById<TextView>(R.id.signin)

            signin.setOnClickListener {
                Intent(this, Pagelogin::class.java).also {
                    startActivity(it) } }

        }

    }

    }
