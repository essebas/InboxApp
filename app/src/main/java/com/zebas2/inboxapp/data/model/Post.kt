package com.zebas2.inboxapp.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(
    tableName = "posts"
)
data class Post(

    @PrimaryKey
    @SerializedName("id")
    val id: Int,

    @SerializedName("userId")
    val userId: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("body")
    val body: String,
    @SerializedName("isRead")
    var isRead: Boolean = false,
    @SerializedName("isFavorite")
    var isFavorite: Boolean = false
) : Serializable
