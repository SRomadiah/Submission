package com.example.submission.ui

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.submission.data.Repository.FavoriteRepository
import com.example.submission.database.FavoriteEntity

class FavoriteViewModel(application: Application) : ViewModel() {
    private val mFavoriteRepository : FavoriteRepository = FavoriteRepository(application)

    fun displayAllFavorite(): LiveData<List<FavoriteEntity>> = mFavoriteRepository.getAllFavoriteUser()
}
