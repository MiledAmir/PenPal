package com.example.penpal


import android.annotation.SuppressLint
import android.graphics.drawable.GradientDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import android.content.Intent
import com.google.firebase.auth.FirebaseAuth

import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import android.os.Build
import android.view.View
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintLayout

import android.widget.CalendarView.OnDateChangeListener
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*

class Homemenu : AppCompatActivity() {

    //variable for the calendar
    lateinit var dateTV: TextView
    lateinit var calendarView: CalendarView

    //variables for the fingerprint auth
    private lateinit var biometricPrompt: BiometricPrompt
    private lateinit var promptInfo: BiometricPrompt.PromptInfo
    //private lateinit var mMainLayout: ConstraintLayout

    private lateinit var auth: FirebaseAuth
    private lateinit var dbref: DatabaseReference
    private lateinit var userArrayList: ArrayList<User>
    private lateinit var userRecyclerView: RecyclerView

    @SuppressLint("MissingInflatedId", "SuspiciousIndentation")
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
        /*
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
        }*/

        // Calendar ya man
        dateTV = findViewById(R.id.idTVDate)
        calendarView = findViewById(R.id.calendarView)

        // on below line we are adding set on
        // date change listener for calendar view.
        calendarView.setOnDateChangeListener(
            OnDateChangeListener { view, year, month, dayOfMonth ->
                // In this Listener we are getting values
                // such as year, month and day of month
                // on below line we are creating a variable
                // in which we are adding all the variables in it.
                val date = (dayOfMonth.toString() + "-"
                        + (month + 1) + "-" + year)

                // set this date in TextView for Display
                dateTV.setText(date)
            })



        userRecyclerView = findViewById(R.id.rv)
        userRecyclerView.layoutManager = LinearLayoutManager(this)
        userRecyclerView.setHasFixedSize(true)
        userArrayList = arrayListOf<User>()
        getUserData()

        val Plus = findViewById<ImageView>(R.id.Plus)
        Plus.setOnClickListener {
            Intent(this, Plusdata::class.java).also {
                startActivity(it)
            }
        }
    }

    private fun getUserData() {
        dbref = FirebaseDatabase.getInstance().getReference("teachers")
        dbref.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (userSnapshot in snapshot.children) {
                        val user = userSnapshot.getValue(User::class.java)
                        userArrayList.add(user!!)
                    }
                    userRecyclerView.adapter = Myadapter(userArrayList)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }


        })


    }


}