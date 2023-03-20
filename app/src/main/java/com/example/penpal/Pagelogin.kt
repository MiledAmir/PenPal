package com.example.penpal

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider


class Pagelogin : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pagelogin)

        auth = FirebaseAuth.getInstance()

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this,gso)

        val googleConnect = findViewById<Button>(R.id.google_connect)

        googleConnect.setOnClickListener{
            signInGoogle()
        }

        private fun signInGoogle(){
            val signInIntent = googleSignInClient.signInIntent
            luncher.lunch(signInIntent)
        }

        private val luncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            result ->
                    if(result.resultCode == Activity.RESULT_OK){
                        val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
                        handleResults(task)
                    }
            private fun handleResults(task: Task<GoogleSignInAccount>) {
                if(task.isSuccessful){
                    val account :GoogleSignInAccount? = task.result
                    if(account !=null){
                        updateUI(account)
                    }
                }else{
                    Toast.makeText(this,task.exception.toString(),Toast.LENGTH_SHORT).show()
                }
            }
            private fun updateUI(account: GoogleSignInAccount) {
                val credential = GoogleAuthProvider.getCredential(account.idToken,null)
                auth.signInWithCredential(credential).addOnCompleteListener {
                    if(it.isSuccessful){
                        val intent : Intent = Intent(this,Homemenu::class.java)
                        intent.putExtra("email",account.email)
                        intent.putExtra("name",account.displayName)
                        startActivity(intent)
                    }else{
                        Toast.makeText(this,it.exception.toString(),Toast.LENGTH_SHORT).show()
                    }
                }
            }


        val signup = findViewById<TextView>(R.id.signup)
        val login = findViewById<Button>(R.id.login_btn)
        val email = findViewById<EditText>(R.id.email)
        val pwd = findViewById<EditText>(R.id.password)
        val error = findViewById<TextView>(R.id.error)

        val correctemail = "abc@gmail.com"
        val correctpwd = "azerty"

        signup.setOnClickListener {
            Intent(this, PageSignIn::class.java).also {
                startActivity(it)
            }
        }

        login.setOnClickListener {
            val txtemail = email.text.toString()
            val txtpwd = pwd.text.toString()
            if (txtemail.trim().isEmpty() || txtpwd.trim().isEmpty()) {
                error.text = "Please fill in the blank spaces"
                error.visibility = View.VISIBLE
            } else {
                if (correctemail == txtemail && correctpwd == txtpwd) {
                    Intent(this, Homemenu::class.java).also {
                        startActivity(it)
                    }
                } else {
                    error.text = "The username or password provided in the request are invalid"
                    error.visibility = View.VISIBLE
                }
            }
        }
    }
}





