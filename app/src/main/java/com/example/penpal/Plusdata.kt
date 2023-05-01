package com.example.penpal

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class Plusdata : AppCompatActivity() {
    private lateinit var ettitree: EditText
    private lateinit var etdatee: EditText
    private lateinit var etdescriptionn: EditText
    private lateinit var buttonadd: Button
    private lateinit var dbRef: DatabaseReference
    private lateinit var returnn :ImageView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_plusdata)

        returnn = findViewById(R.id.returnn)
        ettitree = findViewById(R.id.titree)
        etdatee = findViewById(R.id.datee)
        etdescriptionn = findViewById(R.id.descriptionn)
        buttonadd = findViewById(R.id.buttonadd)
        dbRef = FirebaseDatabase.getInstance().getReference("Stories")

        buttonadd.setOnClickListener() {
            saveStoriesData() }
        returnn.setOnClickListener() {
            Intent(this, Homemenu::class.java).also {
                startActivity(it) } }
    }

    private fun saveStoriesData() {
        val titreee = ettitree.text.toString()
        val datee = etdatee.text.toString()
        val descriptionn = etdescriptionn.text.toString()

        if(titreee.isEmpty()){
            ettitree.error ="Please enter the title" }
        if(datee.isEmpty()){
           etdatee.error="Please entrer the date" }
        if(descriptionn.isEmpty()){
            etdescriptionn.error="Please enter your story" }
        val storiesid=dbRef.push().key!!
        val story =storyId(storiesid,titreee,datee,descriptionn)
        dbRef.child(storiesid).setValue(story)
            .addOnCompleteListener{
                Toast.makeText(this,"Data inserted",Toast.LENGTH_LONG).show()
            etdatee.text.clear()
            etdescriptionn.text.clear()
            ettitree.text.clear()
            }.addOnFailureListener{err ->
                Toast.makeText(this,"Error ${err.message}",Toast.LENGTH_LONG).show()
            }

    }
}