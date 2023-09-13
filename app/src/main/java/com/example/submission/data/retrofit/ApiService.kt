package com.example.submission.data.retrofit

import com.example.submission.data.response.DetailResponse
import com.example.submission.data.response.FollowersResponseItem
import com.example.submission.data.response.GitResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("search/users")
    fun getGitUser(
        @Query("q") id: String
    ): Call<GitResponse>
    @GET("users/{username}/followers")
    fun getFollowers(@Path("username") username: String):Call<List<FollowersResponseItem>>
    @GET("users/{username}/following")
    fun getFollowing(@Path("username") username: String):Call<List<FollowersResponseItem>>
    @GET("users/{username}")
    fun getDetailUser(@Path("username") username: String): Call<DetailResponse>
}