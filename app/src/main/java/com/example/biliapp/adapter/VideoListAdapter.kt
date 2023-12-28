package com.example.biliapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.biliapp.databinding.VideoListItemBinding
import com.example.biliapp.item.VideoItem

class VideoListAdapter(private val data: ArrayList<VideoItem>)
    : RecyclerView.Adapter<VideoListAdapter.ViewHolder>(){
    inner class ViewHolder(val binding: VideoListItemBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(VideoListItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.videoName.text=data[position].name
        holder.binding.videoCover.setImageResource(data[position].img)
    }

    override fun getItemCount() = data.size
}
