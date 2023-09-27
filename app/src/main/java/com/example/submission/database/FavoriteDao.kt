package com.example.submission.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FavoriteDao {
    @Query("SELECT * FROM favorite_user ORDER BY username DESC")
    fun getFavoriteUser(): LiveData<List<FavoriteEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavoriteUser(favoriteUser : FavoriteEntity)

    @Delete
    fun deleteFavoriteUser(favoriteUser: FavoriteEntity)
}