package com.example.penpal

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class Pagelogin : AppCompatActivity() {



    private lateinit var auth: FirebaseAuth
   // private lateinit var googleSignInClient: GoogleSignInClient
    lateinit var inputemail : EditText
    lateinit var inputpassword:EditText
    lateinit var loginBtn :Button
    lateinit var signup :TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pagelogin)
      auth= Firebase.auth
       inputemail =findViewById(R.id.email)
        inputpassword=findViewById(R.id.password)
       loginBtn=findViewById(R.id.login_btn)
       signup=findViewById(R.id.signup)


        //GoogleSignIn
      /*  auth = FirebaseAuth.getInstance()

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)

        val googleConnect = findViewById<Button>(R.id.google_connect)

        googleConnect.setOnClickListener {
            signInGoogle()
        }*/

        //login

    }

    override fun onStart() {
        super.onStart()
        loginBtn.setOnClickListener{
            val email=inputemail.text.toString()
            val password =inputpassword.text.toString()
            if(email.isEmpty() || password.isEmpty()){
                if (password.isEmpty()){
                    inputemail.error="email" }
                if (email.isEmpty()){
                    inputpassword.error="password" }}
            else {signIn(email,password)}
            //  performLogin()
        }
        signup.setOnClickListener {
            Intent(this, PageSignUp::class.java).also {
                startActivity(it)
            }
        }
    }

    fun signIn(email: String, password: String) {

    auth.signInWithEmailAndPassword(email,password).addOnCompleteListener {task ->
        if(task.isSuccessful) {
            Intent(this, Homemenu::class.java).also{
            startActivity(it)
                Toast.makeText(baseContext, "Success", Toast.LENGTH_SHORT).show() }
            finish()
    }else { Toast.makeText(baseContext, "Authentication failed.",
            Toast.LENGTH_SHORT).show() }

    }
<<<<<<< HEAD

    private fun performLogin(){
        val email = findViewById<EditText>(R.id.email)
        val pwd = findViewById<EditText>(R.id.password)

        if (email.text.isEmpty() || pwd.text.isEmpty()){
            Toast.makeText(this,"Please fill all fields",Toast.LENGTH_SHORT).show()
            return
        }

        val inputEmail = email.text.toString()
        val inputPassword = pwd.text.toString()

        auth.signInWithEmailAndPassword(inputEmail, inputPassword)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                        val intent = Intent(this, Homemenu::class.java)
                        startActivity(intent)
                        Toast.makeText(baseContext, "Success", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                }
            }
            .addOnFailureListener{
                Toast.makeText(baseContext, "Authentication failed${it.localizedMessage} .",
                    Toast.LENGTH_SHORT).show()
            }

=======
>>>>>>> 49cc43534cacde58d6f8c81000a777afa11bf01f
    }


    /*  private fun signInGoogle() {
          val signInIntent = googleSignInClient.signInIntent
          launcher.launch(signInIntent)
      }

      private val launcher =
          registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
              if (result.resultCode == Activity.RESULT_OK) {
                  val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
                  handleResults(task)
              }
          }

      private fun handleResults(task: Task<GoogleSignInAccount>) {
          if (task.isSuccessful) {
              val account: GoogleSignInAccount? = task.result
              if (account != null) {
                  updateUI(account)
              }
          } else {
              Toast.makeText(this, task.exception.toString(), Toast.LENGTH_SHORT).show()
          }
      }

      private fun updateUI(account: GoogleSignInAccount) {
          val credential = GoogleAuthProvider.getCredential(account.idToken, null)
          auth.signInWithCredential(credential).addOnCompleteListener {
              if (it.isSuccessful) {
                  val intent = Intent(this, Homemenu::class.java)
                  intent.putExtra("email", account.email)
                  intent.putExtra("name", account.displayName)
                  startActivity(intent)
              } else {
                  Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
              }
          }

      }*/


}


















