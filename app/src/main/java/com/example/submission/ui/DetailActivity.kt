package com.example.submission.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.submission.R
import com.example.submission.data.response.DetailResponse
import com.example.submission.database.FavoriteEntity
import com.example.submission.databinding.ActivityDetailBinding
import com.google.android.material.tabs.TabLayoutMediator

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private lateinit var viewModelFollowers : ViewModelFollowers

    companion object {
        private const val TAG = "DetailActivity"
        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.tab_1,
            R.string.tab_2
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val getUsername = intent.getStringExtra("username")
        viewModelFollowers = ViewModelProvider(this, FollowersViewModelFactory.getInstance(this, getUsername!!, this@DetailActivity.application))[ViewModelFollowers::class.java]

        viewModelFollowers.Loading.observe(this){
            showLoading(it)
        }
        viewModelFollowers.dataDetailUser.observe(this){detail->
            setUserData(detail)

            val sectionPagerAdapter = SectionPagerAdapter(this)
            val viewPager = binding.viewPager
            viewPager.adapter = sectionPagerAdapter
            val tabs = binding.layoutTabs
            TabLayoutMediator(tabs, viewPager){ tab, position ->
                tab.text = resources.getString(TAB_TITLES[position])
            }.attach()
        binding.fabFav.setOnClickListener{
            viewModelFollowers.updateFavorite(FavoriteEntity(
                detail.login!!, detail.avatarUrl
            ))
        }
        }

        Log.d("TAG", "onCreate: $getUsername")

        var favoritedUser : FavoriteEntity = FavoriteEntity(

        )

        viewModelFollowers.getAllFavoriteUser().observe(this){
            it.forEach{
                if(it.username == getUsername){
                    viewModelFollowers.setThisFavorite(true)
                    favoritedUser = it
                }
            }
        }
        viewModelFollowers.thisFavorite.observe(this){
            setIconFavorite(it)
        }

    }

    private fun setIconFavorite(it: Boolean) {
        if(it){
            binding.fabFav?.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.love_filled))
        }else {
            binding.fabFav?.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.love_outline))
        }
    }

    fun setUserData (dataUser : DetailResponse){
        Glide.with(binding.root.context)
            .load(dataUser.avatarUrl)
            .into(binding.profile)
        binding.username.text = dataUser.login
        binding.descuser.text = dataUser.name.toString()
        binding.tvFollowing.text = getString(R.string.following_text, dataUser.following)
        binding.tvFollowers.text = getString(R.string.followers_text, dataUser.followers)
    }
    fun showLoading (isLoading: Boolean){
        if(isLoading){
            binding.progressbar.visibility = View.VISIBLE
        } else {
            binding.progressbar.visibility = View.GONE
        }
    }


}