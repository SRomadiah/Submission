package com.example.submission.ui

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.submission.data.response.DetailResponse
import com.example.submission.data.response.FollowersResponse
import com.example.submission.data.response.ItemsItem
import com.example.submission.data.response.FollowersResponseItem
import com.example.submission.data.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ViewModelFollowers(var username: String): ViewModel() {

        private val _detailUser = MutableLiveData<DetailResponse>()
        val dataDetailUser: LiveData<DetailResponse> = _detailUser

        private val _loading = MutableLiveData<Boolean>()
        val Loading: LiveData<Boolean> = _loading

        private val _listFollowers = MutableLiveData<List<FollowersResponseItem>?>()
        val listFollowers: LiveData<List<FollowersResponseItem>?> = _listFollowers

        private val _listFollowing = MutableLiveData<List<FollowersResponseItem>?>()
        val listFollowing: LiveData<List<FollowersResponseItem>?> = _listFollowing


    init
    {
        getUserDetail()
    }

    fun findDataFollowers (){
        _loading.value = true
        val userFollowers = ApiConfig.getApiService().getFollowers(username)
        userFollowers.enqueue(object : Callback<List<FollowersResponseItem>> {
            override fun onResponse(
                call: Call<List<FollowersResponseItem>>,
                response: Response<List<FollowersResponseItem>>
            ) {
                _loading.value = false
                if (response.isSuccessful){
                    val responseBody = response.body()
                    if (responseBody != null){
                        _listFollowers.value = responseBody
                    }
                }
            }

            override fun onFailure(call: Call<List<FollowersResponseItem>>, t: Throwable) {
                _loading.value = false
            }

        })
    }
    fun findDataFollowing (){
        _loading.value = true
        val userFollowing = ApiConfig.getApiService().getFollowing(username)
        userFollowing.enqueue(object : Callback<List<FollowersResponseItem>> {
            override fun onResponse(
                call: Call<List<FollowersResponseItem>>,
                response: Response<List<FollowersResponseItem>>
            ) {
                _loading.value = false
                if (response.isSuccessful){
                    val responseBody = response.body()
                    if (responseBody != null){
                        _listFollowing.value = responseBody
                    }
                }
            }

            override fun onFailure(call: Call<List<FollowersResponseItem>>, t: Throwable) {
                _loading.value = false
            }

        })
    }

    fun getUserDetail() {
        _loading.value = true
        val userDetail = ApiConfig.getApiService().getDetailUser(username)
        userDetail.enqueue(object : retrofit2.Callback<DetailResponse> {
            override fun onResponse(
                call: Call<DetailResponse>,
                response: Response<DetailResponse>
            ) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        _loading.value = false
                        _detailUser.value = response.body()
                    } else {
                        Log.d("FollowersViewModel", "onFailure: ${response.message()}")
                    }
                }
            }

            override fun onFailure(call: Call<DetailResponse>, t: Throwable) {
                _loading.value = false
                Log.d("FollowersViewModel", "onFailure: ${t.message}")
            }
        })
    }
}

class FollowersViewModelFactory private constructor(
    private val selectedUser: String
) :
    ViewModelProvider.Factory {

    companion object {
        @Volatile
        private var instance: FollowersViewModelFactory? = null

        fun getInstance(context: Context, selectedUser: String): FollowersViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: FollowersViewModelFactory(
                    selectedUser
                )
            }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        when {
            modelClass.isAssignableFrom(ViewModelFollowers::class.java) -> {
                ViewModelFollowers(selectedUser) as T
            }

            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
}

