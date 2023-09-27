package com.example.submission.database

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity("favorite_user")
@Parcelize
class FavoriteEntity (
    @PrimaryKey(autoGenerate = false)
    var username: String = "",

    var avatarUrl: String? = null,
        ) : Parcelable
