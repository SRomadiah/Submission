package com.example.submission.ui

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.submission.data.Repository.FavoriteRepository
import com.example.submission.data.response.DetailResponse
import com.example.submission.data.response.FollowersResponseItem
import com.example.submission.data.retrofit.ApiConfig
import com.example.submission.database.FavoriteEntity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ViewModelFollowers(
    var username: String,
    application: Application
    ): ViewModel() {

        private val mFavoriteRepository: FavoriteRepository = FavoriteRepository(application)

        private val _detailUser = MutableLiveData<DetailResponse>()
        val dataDetailUser: LiveData<DetailResponse> = _detailUser

        private val _loading = MutableLiveData<Boolean>()
        val Loading: LiveData<Boolean> = _loading

        private val _listFollowers = MutableLiveData<List<FollowersResponseItem>?>()
        val listFollowers: LiveData<List<FollowersResponseItem>?> = _listFollowers

        private val _listFollowing = MutableLiveData<List<FollowersResponseItem>?>()
        val listFollowing: LiveData<List<FollowersResponseItem>?> = _listFollowing

        private val _thisFavorite = MutableLiveData<Boolean>()
        val thisFavorite: LiveData<Boolean> = _thisFavorite




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

    fun getAllFavoriteUser():LiveData<List<FavoriteEntity>> = mFavoriteRepository.getAllFavoriteUser()

    fun setThisFavorite(thisFavorite: Boolean){
        _thisFavorite.value = thisFavorite
    }
    fun insertFavorite(thisFavorite : FavoriteEntity){
        setThisFavorite(true)
        Log.d("Pesan", "Ini dari viewmodel")
        mFavoriteRepository.insertFavoriteUser(thisFavorite)

    }
    fun deleteFavorite(thisFavorite: FavoriteEntity){
        setThisFavorite(false)
        mFavoriteRepository.deleteFavoriteUser(thisFavorite)
    }
    fun updateFavorite(favorited: FavoriteEntity){
        if(thisFavorite.value != true){
            Log.d("Pesan", "ini dari if, fun updatefav")
            insertFavorite(favorited)
        }else{
            Log.d("Pesan", "ini dari else, fun updatefav")
            deleteFavorite(favorited)
        }
    }

}



class FollowersViewModelFactory private constructor(
    private val selectedUser: String,
    private val mApplication: Application
) :
    ViewModelProvider.Factory {

    companion object {
        @Volatile
        private var instance: FollowersViewModelFactory? = null

        fun getInstance(context: Context, selectedUser: String, mApplication: Application): FollowersViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: FollowersViewModelFactory(
                    selectedUser,
                    mApplication
                )
            }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        when {
            modelClass.isAssignableFrom(ViewModelFollowers::class.java) -> {
                ViewModelFollowers(selectedUser, mApplication) as T
            }

            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
}

