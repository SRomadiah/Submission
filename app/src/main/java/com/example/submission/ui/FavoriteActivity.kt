package com.example.submission.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.submission.R
import com.example.submission.data.response.ItemsItem
import com.example.submission.database.FavoriteEntity
import com.example.submission.databinding.ActivityFavoriteBinding
import com.example.submission.databinding.ActivityMainBinding
import com.example.submission.ui.Github.Adapter

class FavoriteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavoriteBinding
    private val viewModel by viewModels<FavoriteViewModel>()
    private lateinit var rvUserFavorite: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        rvUserFavorite = binding.rvUser

        val layoutManager = LinearLayoutManager(this)
        binding.rvUser.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        binding.rvUser.addItemDecoration(itemDecoration)

        viewModel.displayAllFavorite().observe(this){
        userFavorite ->
        }


        setContentView(binding.root)
    }

    fun setUserData(dataUser : List<ItemsItem>){
        val adapter = Adapter()
        adapter.submitList(dataUser)
        binding.rvUser.adapter = adapter

        adapter.seOnItemClickCallback(object : Adapter.OnItemClickCallback{
            override fun onItemClickced(data: ItemsItem) {
                val intentDetail = Intent(this@FavoriteActivity, DetailActivity::class.java)
                intentDetail.putExtra("username", data.login)
                startActivity(intentDetail)
            }

        })

    }
}