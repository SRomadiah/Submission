package com.example.submission.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil.ItemCallback
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.submission.data.response.FollowersResponseItem
import com.example.submission.databinding.DataItemsBinding

class FollowersAdapter : ListAdapter<FollowersResponseItem, FollowersAdapter.FollowersViewHolder>(DIFF_CALLBACK){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowersViewHolder {
        val binding = DataItemsBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return FollowersViewHolder(binding)
    }


    override fun onBindViewHolder(holder: FollowersViewHolder, position: Int) {
        val follower = getItem(position)
        holder.bind(follower)
    }

    inner class FollowersViewHolder(private val binding: DataItemsBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(followers: FollowersResponseItem){

            binding.username.text = followers.login
            Glide.with(binding.root.context)
//                    ambil data dari github
                .load(followers.avatarUrl)
//                    menyimpan data ke layout
                .into(binding.profileUser)
        }

    }

    companion object {
        private val DIFF_CALLBACK= object : ItemCallback<FollowersResponseItem>() {
            override fun areItemsTheSame(oldItem: FollowersResponseItem, newItem: FollowersResponseItem): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: FollowersResponseItem, newItem: FollowersResponseItem): Boolean {
                return oldItem.id == newItem.id
            }

        }
    }

}