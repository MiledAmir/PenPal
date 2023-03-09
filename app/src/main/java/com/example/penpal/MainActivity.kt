package com.example.penpal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

        view_pager2.adapter = ViewPagerAdapter(handsList,arrowList)
        view_pager2.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        val indicator = findViewById<CircleIndicator3>(R.id.indicator)
        indicator.setViewPager(view_pager2)
    }

    private fun addToList(hands:Int,arrow:Int){
        handsList.add(hands)
        arrowList.add(arrow)
    }

    private fun postToList(){
        for (i in 1..2){
            addToList((R.mipmap.ic_launcher_round))
        }
    }
}