package com.example.penpal


import android.graphics.drawable.GradientDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.content.ContextCompat
import android.content.Intent
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth

import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import android.os.Build
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintLayout

class Homemenu : AppCompatActivity() {

    private lateinit var biometricPrompt: BiometricPrompt
    private lateinit var promptInfo: BiometricPrompt.PromptInfo
    //private lateinit var mMainLayout: ConstraintLayout

    private lateinit var auth: FirebaseAuth

    @RequiresApi(api = Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_homemenu)

        // FingerPrint Authentication

        val mMainLayout = findViewById<ConstraintLayout>(R.id.main)
        val biometricManager = BiometricManager.from(this)

        when (biometricManager.canAuthenticate()) {
            BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE -> Toast.makeText(
                applicationContext,
                "Device doesn't have fingerprint",
                Toast.LENGTH_SHORT
            ).show()
            BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE -> Toast.makeText(
                applicationContext,
                "Not working",
                Toast.LENGTH_SHORT
            ).show()
            BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED -> Toast.makeText(
                applicationContext,
                "No fingerprint enrolled",
                Toast.LENGTH_SHORT
            ).show()
        }

        val executor = ContextCompat.getMainExecutor(this)

        biometricPrompt = BiometricPrompt(
            this,
            executor,
            object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationError(
                    errorCode: Int,
                    errString: CharSequence
                ) {
                    super.onAuthenticationError(errorCode, errString)
                }

                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                    super.onAuthenticationSucceeded(result)
                    Toast.makeText(
                        applicationContext,
                        "Hello",
                        Toast.LENGTH_SHORT
                    ).show()
                    mMainLayout.visibility = View.VISIBLE
                }

                override fun onAuthenticationFailed() {
                    super.onAuthenticationFailed()
                }

                /*public fun onBackPressed() {
                    // Finish the activity and return to the home screen
                    finish()
                }*/
            })

        promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle("Project")
            .setDescription("Use fingerprint to get in")
            .setDeviceCredentialAllowed(true)
            .build()

        biometricPrompt.authenticate(promptInfo)

        // Grabbing Username & Signout

        auth = FirebaseAuth.getInstance()

        val email = intent.getStringExtra("email")
        val displayName = intent.getStringExtra("name")

        findViewById<TextView>(R.id.userid).text = email + displayName
       var userid = findViewById<TextView>(R.id.userid)
        var name = getIntent().getStringExtra("myname")
        userid.setText(name)
        findViewById<Button>(R.id.signout).setOnClickListener {
            auth.signOut()
            startActivity(Intent(this, Pagelogin::class.java))
        }
    }
}