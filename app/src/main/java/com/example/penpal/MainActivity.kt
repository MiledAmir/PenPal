package com.example.penpal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import me.relex.circleindicator.CircleIndicator3

class MainActivity : AppCompatActivity() {
    private var handsList = mutableListOf<Int>()
    private var arrowList = mutableListOf<Int>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        postToList()

        val vp2 = findViewById<ViewPager2>(R.id.viewpager)

        vp2.adapter = ViewPagerAdapter(handsList,arrowList)
        vp2.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        val indicator = findViewById<CircleIndicator3>(R.id.indicator)
        indicator.setViewPager(vp2)

        val arrow = findViewById<ImageView>(R.id.mainpagearrow)

        arrow.setOnClickListener{
            Intent(this,HomePenPal::class.java).also{
                startActivity(it) }
        }
    }

    private fun addToList(hands:Int,arrow:Int){
        handsList.add(hands)
        arrowList.add(arrow)
    }

    private fun postToList(){
        for (i in 1..2){
            addToList(R.drawable.hands,R.drawable.arrowright)
        }
    }
}