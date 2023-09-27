package com.example.submission.ui

import android.os.SystemClock
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.submission.data.response.GitResponse
import com.example.submission.data.response.ItemsItem
import com.example.submission.data.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.Timer
import java.util.TimerTask

class MainViewModel: ViewModel() {
    private val _dataProfile = MutableLiveData<List<ItemsItem>>()
    val dataProfile: LiveData<List<ItemsItem>> = _dataProfile
    private val _loading = MutableLiveData<Boolean>()
    val Loading: LiveData<Boolean> = _loading

    init {
        findGitUser()
    }
    fun findGitUser(userGit:String = "Roma"){
        _loading.value = true
        val user = ApiConfig.getApiService().getGitUser(userGit)
        user.enqueue(object : Callback<GitResponse>{
            override fun onResponse(call: Call<GitResponse>, response: Response<GitResponse>) {
                _loading.value = false
                if (response.isSuccessful){
                    val responseBody = response.body()
                    _dataProfile.value = responseBody!!.items as List<ItemsItem>
                } else {
                    Log.d("TAG", "onResponse: ${response.toString()}")
                }

            }

            override fun onFailure(call: Call<GitResponse>, t: Throwable) {
                _loading.value = false
                Log.d("TAG", "onError: ${t.message}")
            }

        })
    }


}
