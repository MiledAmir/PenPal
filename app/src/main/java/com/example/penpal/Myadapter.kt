package com.example.penpal

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Myadapter(private  val storyIdList :ArrayList<storyId>) :RecyclerView.Adapter<Myadapter.MyViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =LayoutInflater.from(parent.context).inflate(R.layout.mainitem,parent,false)
    return  MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return storyIdList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentitem = storyIdList[position]
        holder.titree.text=currentitem.titree
        holder.datee.text=currentitem.datee
        holder.descriptionn.text=currentitem.descriptionn
    }


    class MyViewHolder(itemView : View):RecyclerView.ViewHolder(itemView){
        val descriptionn :TextView = itemView.findViewById(R.id.descriptionn)
        val datee :TextView =itemView.findViewById(R.id.datee)
        val titree  : TextView=itemView.findViewById(R.id.titree)

    }

}


