package com.example.submission.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.submission.R
import com.example.submission.data.response.ItemsItem
import com.example.submission.databinding.ActivityMainBinding
import com.example.submission.ui.Github.Adapter

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val MainViewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val layoutManager = LinearLayoutManager(this)
        binding.rvListItem.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        binding.rvListItem.addItemDecoration(itemDecoration)

        MainViewModel.Loading.observe(this){
            loading(it)
        }
        MainViewModel.dataProfile.observe(this){
            setUserData(it)
        }

        with(binding) {
            searchView.setupWithSearchBar(searchBar)
            searchView.editText.setOnEditorActionListener {v, actionId, event ->
                searchBar.text = searchView.text
                searchView.hide()
                MainViewModel.findGitUser(searchView.text.toString())
                false
            }
        }

    }
    fun setUserData(dataUser : List<ItemsItem>){
        val adapter = Adapter()
        adapter.submitList(dataUser)
        binding.rvListItem.adapter = adapter

        adapter.seOnItemClickCallback(object : Adapter.OnItemClickCallback{
            override fun onItemClickced(data: ItemsItem) {
                val intentDetail = Intent(this@MainActivity, DetailActivity::class.java)
                intentDetail.putExtra("username", data.login)
                startActivity(intentDetail)
            }

        })

    }
    fun loading(isLoading : Boolean){
        if (isLoading){
            binding.load.visibility = View.VISIBLE
        } else {
            binding.load.visibility = View.GONE
        }
    }

}