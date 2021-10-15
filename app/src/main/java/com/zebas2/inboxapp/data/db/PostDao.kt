package com.zebas2.inboxapp.data.db

import androidx.room.*
import com.zebas2.inboxapp.data.model.Post
import kotlinx.coroutines.flow.Flow

@Dao
interface PostDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertList(posts: List<Post>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(post: Post)

    @Update
    suspend fun updatePost(post: Post)

    @Query("SELECT * FROM posts")
    suspend fun getPosts(): List<Post>

    @Query("SELECT * FROM posts WHERE isFavorite LIKE :favoritePost")
    suspend fun getFavoritesPosts(favoritePost: Int): List<Post>

    @Delete
    suspend fun deletePost(post: Post)

}