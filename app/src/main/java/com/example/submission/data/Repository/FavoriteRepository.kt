package com.example.submission.data.Repository

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import com.example.submission.database.FavoriteDao
import com.example.submission.database.FavoriteDatabase
import com.example.submission.database.FavoriteEntity
import java.util.concurrent.Executor
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class FavoriteRepository(application: Application) {
    private val mFavoriteDao: FavoriteDao

    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    init {
        val db = FavoriteDatabase.getDatabase(application)
        mFavoriteDao = db.favoriteDao()
    }

    fun getAllFavoriteUser(): LiveData<List<FavoriteEntity>> = mFavoriteDao.getFavoriteUser()

    fun insertFavoriteUser(favoriteUser: FavoriteEntity){
        Log.d("MSG", favoriteUser.username)
        executorService.execute{mFavoriteDao.insertFavoriteUser(favoriteUser)}
    }

    fun deleteFavoriteUser(favoriteUser: FavoriteEntity){
        executorService.execute{mFavoriteDao.deleteFavoriteUser(favoriteUser)}
    }

}