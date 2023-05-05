package com.example.penpal

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Myadapter() :RecyclerView.Adapter<Myadapter.ViewHolder>(){


    var items:MutableList<storyId> = mutableListOf()
           set(value){
               field=value
              notifyDataSetChanged()
           }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       val itemView =LayoutInflater.from(parent.context).inflate(R.layout.mainitem,parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount()=items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val storie=items[position]
        holder.bind(storie)
    }

    class ViewHolder(itemView :View):RecyclerView.ViewHolder(itemView){
           val titre:TextView=itemView.findViewById(R.id.titree)
        val date:TextView=itemView.findViewById(R.id.datee)
        val description:TextView=itemView.findViewById(R.id.descriptionn)
        fun bind(storie:storyId){
            titre.text=storie.titree
            date.text=storie.datee
            description.text=storie.descriptionn
        }


    }


    }













/*Myadapter(private  val storyIdList :ArrayList<storyId>) :RecyclerView.Adapter<Myadapter.MyViewHolder>(){


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

    }}*/




