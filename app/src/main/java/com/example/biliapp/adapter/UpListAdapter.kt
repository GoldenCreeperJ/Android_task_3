package com.example.biliapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.biliapp.databinding.UpListItemBinding
import com.example.biliapp.item.UpItem

class UpListAdapter(private val data:ArrayList<UpItem>)
    : RecyclerView.Adapter<UpListAdapter.ViewHolder>() {
    interface OnClickListener {fun onClick(position: Int)}
    interface OnLongClickListener {fun onLongClick(position: Int)}

    var clickListener: OnClickListener? = null
    var longClickListener: OnLongClickListener? = null

    inner class ViewHolder(val binding: UpListItemBinding): RecyclerView.ViewHolder(binding.root){
        init {
            binding.upListName.setOnClickListener {clickListener?.onClick(bindingAdapterPosition)}
            binding.upListImg.setOnClickListener {clickListener?.onClick(bindingAdapterPosition)}
            binding.upListName.setOnLongClickListener {
                longClickListener?.onLongClick(bindingAdapterPosition)
                true
            }
            binding.upListImg.setOnLongClickListener {
                longClickListener?.onLongClick(bindingAdapterPosition)
                true
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(UpListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.upListName.text=data[position].name
        holder.binding.upListImg.setImageResource(data[position].img)
    }

    fun deleteUp(name: String){
        for (i in data.withIndex()) {
            if (i.value.name == name){
                data.remove(i.value)
                this.notifyItemRemoved(i.index)
                break
            }
        }
    }

    override fun getItemCount()=data.size
}
