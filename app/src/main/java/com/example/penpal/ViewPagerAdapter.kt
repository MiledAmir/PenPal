package com.example.penpal

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class ViewPagerAdapter(private var hands:List<Int>,private var arrow:List<Int>):RecyclerView.Adapter<ViewPagerAdapter.Pager2ViewHolder>() {

    inner class Pager2ViewHolder(ItemView:View):RecyclerView.ViewHolder(ItemView){
        val itemHands :ImageView = itemView.findViewById(R.id.hands)
        val itemArrow :ImageView = itemView.findViewById(R.id.arrow)

        init {
            itemHands.setOnClickListener{v: View ->
                val position = adapterPosition
                Toast.makeText(itemView.context,"you clicked on the hands${position+1}",Toast.LENGTH_LONG).show()

            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerAdapter.Pager2ViewHolder {
        return Pager2ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.activity_home_pen_pal,parent,false))
    }

    override fun getItemCount(): Int {
        return 2
    }

    override fun onBindViewHolder(holder: ViewPagerAdapter.Pager2ViewHolder, position: Int) {
        holder.itemHands.setImageResource(hands[position])
        holder.itemArrow.setImageResource(arrow[position])

    }
}