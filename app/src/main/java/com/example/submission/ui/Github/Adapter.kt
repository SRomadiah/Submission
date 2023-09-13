package com.example.submission.ui.Github

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.submission.data.response.ItemsItem
import com.example.submission.databinding.DataItemsBinding

class Adapter: ListAdapter<ItemsItem, Adapter.MyViewHolder>(DIFF_CALLBACK) {

    private lateinit var onItemClickCallback: OnItemClickCallback
    interface OnItemClickCallback {

        fun onItemClickced(data: ItemsItem)
    }
//    membuat fungsi click
    fun seOnItemClickCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }
    class MyViewHolder(val binding: DataItemsBinding):RecyclerView.ViewHolder(binding.root) {

        fun bind(display:ItemsItem){
            binding.username.text=display.login
            Glide.with(binding.root.context)
//                    ambil data dari github
                .load(display.avatarUrl)
//                    menyimpan data ke layout
                .into(binding.profileUser)


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = DataItemsBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(binding)
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
       val dataItems = getItem(position)
        holder.bind(dataItems)
        holder.itemView.setOnClickListener{
            onItemClickCallback.onItemClickced(getItem(holder.adapterPosition))
        }
    }
    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ItemsItem>(){
            override fun areItemsTheSame(oldItem: ItemsItem, newItem: ItemsItem): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: ItemsItem, newItem: ItemsItem): Boolean {
                return oldItem == newItem
            }

        }
    }
}




