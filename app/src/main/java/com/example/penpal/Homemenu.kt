package com.example.penpal


import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import android.content.Intent
import com.google.firebase.auth.FirebaseAuth

import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import android.os.Build
import android.util.Log
import android.view.View
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintLayout

import android.widget.CalendarView.OnDateChangeListener
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class Homemenu : AppCompatActivity() {

    //variable for the calendar
    lateinit var dateTV: TextView
    lateinit var calendarView: CalendarView

    //variables for the fingerprint auth
    private lateinit var biometricPrompt: BiometricPrompt
    private lateinit var promptInfo: BiometricPrompt.PromptInfo
    //private lateinit var mMainLayout: ConstraintLayout
    private lateinit var db:FirebaseFirestore
    private lateinit var auth: FirebaseAuth
    private lateinit var dbref: DatabaseReference
    private var currentUser:FirebaseUser? = null
    private lateinit var storyIdArrayList: ArrayList<storyId>

    private lateinit var bienvenue:TextView
    private lateinit var rv:RecyclerView
    private lateinit var storieadapter:Myadapter
    @SuppressLint("MissingInflatedId", "SuspiciousIndentation")
    @RequiresApi(api = Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_homemenu)

        rv =findViewById(R.id.rv)
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

            })

        promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle("Project")
            .setDescription("Use fingerprint to get in")
            .setDeviceCredentialAllowed(true)
            .build()

        biometricPrompt.authenticate(promptInfo)


        // Calendar ya man
        dateTV = findViewById(R.id.idTVDate)
        calendarView = findViewById(R.id.calendarView)

        // on below line we are adding set on
        // date change listener for calendar view.
        calendarView.setOnDateChangeListener(
            OnDateChangeListener { view, year, month, dayOfMonth ->

                val date = (dayOfMonth.toString() + "-"
                        + (month + 1) + "-" + year)

                // set this date in TextView for Display
                dateTV.setText(date)
            })

        bienvenue =findViewById(R.id.userid)
        auth = Firebase.auth
        db = Firebase.firestore
        currentUser = auth.currentUser
        if (currentUser != null) {
            db.collection("users").document(currentUser!!.uid).get()
                .addOnSuccessListener { result ->
                    if (result != null) {
                        var user = result.toObject(User::class.java)
                        user?.let{
                            user.uuid= currentUser!!.uid
                            bienvenue.setText(user.fullname)
                            
                        }
                    }
                } } else {
       Log.d("HomemenuActivity","No user found")
        }






        val Plus = findViewById<ImageView>(R.id.Plus)
        Plus.setOnClickListener {
            Intent(this, Plusdata::class.java).also {
                startActivity(it)
            }
        }

        val logout=findViewById<ImageView>(R.id.logout)
        logout.setOnClickListener{
            val auth=Firebase.auth
            auth.signOut()
            Intent(this,Pagelogin::class.java).also{
                startActivity(it) }
            finish()
        }
        val stories = mutableListOf<storyId>()
        db.collection("stories").document(currentUser!!.uid).get()
            .addOnSuccessListener {result->
                if (result != null) {
                val uuid =result.id
                val titre=result.getString("titre")
                val   description=result.getString("description")
                val   date=result.getString("date")
            stories.add(storyId(uuid, date?:"",description?:"", titre?:""))
             }}
        .addOnFailureListener{exception ->
            Log.e("Homemenu","erreor",exception)
        }


        val Myadapter= Myadapter()

        rv.apply {
            layoutManager= LinearLayoutManager(this@Homemenu)
            adapter= Myadapter
        }
        Myadapter.items = stories
    }










}




