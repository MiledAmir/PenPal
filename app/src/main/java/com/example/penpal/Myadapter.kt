package com.example.penpal

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import de.hdodenhof.circleimageview.CircleImageView

class Myadapter(private  val userList :ArrayList<User>) :RecyclerView.Adapter<Myadapter.MyViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =LayoutInflater.from(parent.context).inflate(R.layout.mainitem,parent,false)
    return  MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentitem = userList[position]
        holder.course.text=currentitem.course
        holder.email.text=currentitem.email
        holder.name.text=currentitem.name
    }


    class MyViewHolder(itemView : View):RecyclerView.ViewHolder(itemView){
        val course :TextView = itemView.findViewById(R.id.coursetext)
        val email :TextView =itemView.findViewById(R.id.emailtext)
        val name  : TextView=itemView.findViewById(R.id.nametext)

    }

}


