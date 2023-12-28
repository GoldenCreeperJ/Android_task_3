package com.example.biliapp.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.biliapp.databinding.IntermediateLayoutBinding
import com.example.biliapp.item.IntermediateItem

class IntermediateAdapter(private val data:ArrayList<IntermediateItem>)
    : RecyclerView.Adapter<IntermediateAdapter.ViewHolder>(){
    inner class ViewHolder(val binding: IntermediateLayoutBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(IntermediateLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.intermediate.layoutManager= LinearLayoutManager(holder.binding.root.context)
        holder.binding.intermediate.adapter= VideoListAdapter(data[position].content!!)
        holder.binding.videoListName.text="${data[position].owner}的视频动态"
    }

    fun deleteVideo(name: String){
        for (i in data.withIndex()) {
            if (i.value.owner == name){
                data.remove(i.value)
                this.notifyItemRemoved(i.index)
                break
            }
        }
    }

    override fun getItemCount() = data.size
}
