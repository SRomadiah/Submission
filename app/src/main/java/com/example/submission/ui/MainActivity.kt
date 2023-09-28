package com.example.submission.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.CompoundButton
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatDelegate
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewbinding.ViewBinding
import com.example.submission.R
import com.example.submission.data.response.ItemsItem
import com.example.submission.databinding.ActivityMainBinding
import com.example.submission.ui.Github.Adapter
import com.google.android.material.switchmaterial.SwitchMaterial



//val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModelFollowers : ViewModelFollowers


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
            Log.d("TAG", "onResponse: $it")
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
        val pref = SettingPreference.getInstance(dataStore)

        val mainViewModel = ViewModelProvider(this, ViewModelFollowers(pref)).get(
            MainViewModel::class.java
        )

        mainViewModel.getThemeSettings().observe(this) { isDarkModeActive: Boolean ->
            if (isDarkModeActive) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }


        setContentView(binding.root)
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
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.setting -> {
                val intent = Intent(this, SettingActivity::class.java)
                startActivity(intent)
                true
            }
            R.id.favorite -> {
                val intent = Intent(this, FavoriteActivity::class.java)
                startActivity(intent)
                true
            }
            else -> true
        }
    }

}

