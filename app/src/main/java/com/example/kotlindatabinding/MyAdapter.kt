package com.example.kotlindatabinding

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlindatabinding.databinding.HobbyItemViewBinding

class MyAdapter(var hobbyList: List<HobbyModel>,val listener: MyInterface) : RecyclerView.Adapter<MyAdapter.MyViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
       val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<HobbyItemViewBinding>(layoutInflater, R.layout.hobby_item_view, parent,false)

        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.hobbyItemView.item = hobbyList[position]

        holder.hobbyItemView.icShare.setOnClickListener(){
            listener.onClick(hobbyList[position])
        }
    }

    override fun getItemCount(): Int = hobbyList.size

    inner class MyViewHolder(var hobbyItemView: HobbyItemViewBinding) : RecyclerView.ViewHolder(hobbyItemView.root)

}